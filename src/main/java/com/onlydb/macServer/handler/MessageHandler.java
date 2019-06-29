package com.onlydb.macServer.handler;

import com.onlydb.data.mac.dao.TestMapper;
import com.onlydb.data.mac.entity.JZTZ;
import com.onlydb.data.mac.entity.JZXX;
import com.onlydb.data.mac.entity.NormalSJ;
import com.onlydb.data.mac.entity.XHMS;
import com.onlydb.global.prop.SocketPool;
import com.onlydb.macServer.MessageServer;
import com.onlydb.util.CRC16Util;
import com.onlydb.util.SJGZUtil;
import com.onlydb.util.SocketUtil;
import com.onlydb.util.TransUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledDirectByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MessageHandler extends AMessageHandler {


//    private String command = "";
//    private boolean validAll = false;
    @Autowired
    private ExecutorService bussExecutor;

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//        ByteBuf b = Unpooled.buffer();
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;
            if(event.state() == IdleState.READER_IDLE) {
                if(valid==false){
                    logger.error("连接未验证，强制断开");
                    ctx.close();
                } else if(cur>90){
                    logger.error("15分钟内未收到信息，强制关闭连接");
                    ctx.close();
                }
                cur++;
            }
        }else {
            super.userEventTriggered(ctx,evt);
        }

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        String[] info = SocketUtil.getNetMes(ctx);
        ip = info[0];
        port = info[1];
        address = ip+":"+port;
        jzxx.setJzip(ip);
        jzxx.setJzport(port);
        SocketPool.addInvalid(address,this);
        logger.info(address+"已加入非法连接库");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        bussExecutor.submit(()->{
            byte[] bytes = msgToByte(msg);
            cur = 0;
            logger.info("JZ:"+jzxx.getJzid()+":"+CRC16Util.getBufHexStr(bytes));
            try {
                if(!valid){
                    if(passValid(bytes)){
                        valid = true;
                        saveJzxx(bytes);
                        if(jzxx.getId()!=null) logger.info("jqxx数据库插入成功");
                        else {
                            logger.error("jqxx数据库插入失败");
                            ctx.close();
                        }
                        TransUtil.state.put(address,0);
                        TransUtil.mes.put(address,"");
                        this.ctx = ctx;
                        SocketPool.addValid(address,jzxx.getId(),this);
                        logger.warn(address+"已在非法库中删除");
                        logger.warn(address+"已加入合法连接库");
                    }else{
                        ctx.close();
                    }

                } else {
                    if(CRC16Util.checkBuf(bytes)){
                        saveAll(bytes);
                    }
                }
            } finally {
                ctx.flush();
                ReferenceCountUtil.release(msg);
            }
        });

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        if(SocketPool.removeInvalid(address,this)){
            logger.info(address+"已在非法库中删除");
        } else {
            deleteJzxx();
            SocketPool.removeValid(address,this);
            logger.info(address+"已在合法库中删除");
        }
        TransUtil.removeIp(address);
        logger.warn("JZ:"+jzxx.getJzid()+":"+address+"已删除");
        ctx.fireChannelInactive();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        logger.error("系统出现异常，请检查"+cause);
        ctx.fireExceptionCaught(cause);
        ctx.close();
    }

    private boolean saveJzxx(byte[] bytes){
        String jzsj = CRC16Util.getBufHexStr(Arrays.copyOfRange(bytes,3,bytes.length));
        if(jzsj.length()<38) return false;
        jzxx.setJzid(jzsj.substring(0,4));
        jzxx.setId(testMapper.getJzUUID(jzxx.getJzid()));
        if(jzxx.getId()==null||jzxx.getId()=="") return false;
        Integer num = Integer.parseInt(jzsj.substring(4,6),16);
        jztz = testMapper.getJztj(jzxx);
        if(jztz==null) return false;
        for(int i=0;i<num;i++){
            normalSJs.put("0"+Integer.toHexString(i+1),new NormalSJ(testMapper.getJqUUID(jzxx.getId(),"0"+Integer.toHexString(i+1))));
            loadJqgz("0"+Integer.toHexString(i+1),jzsj.substring(6+i*2,8+i*2));
//            xhmss.put("0"+Integer.toHexString(i+1),testMapper.getXhmsByXh(jzsj.substring()));
        }
        testMapper.insertJzxx(jzxx,"0");
        testMapper.updateJzxx(jzxx,new Date(),"0");
        return true;
    }

    private boolean deleteJzxx(){
        TransUtil.removeIp(address);
        if(jzxx.getId()!=null&&testMapper.validIp(jzxx)>0) {
            testMapper.insertJzxx(jzxx,"1");
            testMapper.updateJzxx(jzxx,new Date(),"1");
            testMapper.unconnectedJq(jzxx,"1");
        }else{
            return false;
        }
        return true;
    }

    private boolean saveJqxx(String re){
        if (re!=null&&re.length()>=18){
            if(re.substring(0,12).equals(jztz.getNormalcom())){
//                String jqxx = CRC16Util.getBufHexStr(Arrays.copyOfRange(bytes,3,bytes.length-2));
                String jqxx = re.substring(14,re.length()-4);
                Integer jqsl = Integer.parseInt(re.substring(12,14));
                if(jqxx.length()!=jqsl*jztz.getSinsjlen());
                for(int b=0;b<jqsl;b++){
//                    int start = b*34;
//                    int end = (b+1)*34;
                    int start = b*jztz.getSinsjlen();
                    String jqbh = jqxx.substring(start,start+2);
                    int end = (b+1)*jztz.getSinsjlen();
                    normalSJs.get(jqbh).setProp(jqxx.substring(start,end),jztz);
                    testMapper.updateJqsj(normalSJs.get(jqbh),new Date());
                    testMapper.inserJqsj(normalSJs.get(jqbh),jzxx);
                }
                return true;
            }
        }
        return false;
    }

    //获取水泵数据
    private boolean savePumb(String re){
        if(re!=null&&re.length()>=18){
            if(re.substring(0,12).equals(jztz.getPumpcom())){
                jzxx.setSbzt((String) SJGZUtil.transMsg(re.substring(12,12+jztz.getPumplen()),jztz.getPumpgz()));
                testMapper.updateSBZT(jzxx);
                testMapper.insertSBZT(jzxx,"0");
                return true;
            }
        }
        return false;
    }

    //获取电表数据
    private boolean saveElectry(String re){
        if(re!=null&&re.length()>=20){
            if(re.substring(0,12).equals(jztz.getEleccom())){
                jzxx.setDbds(SJGZUtil.transMsg(re.substring(12,12+jztz.getEleclen()),jztz.getElecgz()));
                testMapper.updateSBZT(jzxx);
                return true;
            }
        }
        return false;
    }

    private void saveAll(byte[] bytes){
        String re = CRC16Util.getBufHexStr(bytes);
        if(TransUtil.state.get(address).equals(1)){
            if(TransUtil.zls.get(address).equals(re.substring(0,TransUtil.zls.get(address).length()))){
                updateCom(re);
                special(re);
                TransUtil.mes.put(address,re);
                TransUtil.state.put(address,0);
                logger.info(address+"请求参数插入成功");
            }
        } else if(TransUtil.state.get(address).equals(2)){
            Integer i = TransUtil.validQueue(address,re);
            if(i==1) {
                updateCom(re);
                logger.warn(address+"单条指令执行完毕");
            } else if(i==0) {
                updateCom(re);
                logger.info(address+"全部指令执行完毕");
                TransUtil.state.put(address,0);
            } else if(i==2) {
                logger.error(address+"指令异常");
                TransUtil.state.put(address,0);
            }
//            validCommand(re);
        } else if(TransUtil.state.get(address).equals(3)){
            try {
                saveJqxx(re);
                logger.info(address+"常用参数插入成功");
            } finally {
                TransUtil.state.put(address,0);
            }
        } else if(TransUtil.state.get(address).equals(4)){
            try {
                savePumb(re);
                logger.info(address+"机组水泵状态插入成功");
            } finally {
                TransUtil.state.put(address,0);
            }
        } else if(TransUtil.state.get(address).equals(5)){
            try {
                saveElectry(re);
                logger.info(address+"机组电表读数插入成功");
            } finally {
                TransUtil.state.put(address,0);
            }
        } else {
            try {
                if(saveJqxx(re)||saveElectry(re)||savePumb(re))
                logger.info(address+"机器主动数据接收成功");
            } finally {

            }
        }
    }

    private void special(String re){
        if(!saveJqxx(re)){

        }
//        if(re.substring(2,12).equals("0300000077")){
//            NormalSJ normalSJ = new NormalSJ();
//            normalSJ.setJqid(testMapper.getJqUUID(jzxx.getId(),re.substring(0,2)));
//            normalSJ.setMsxz(re.substring(14,16));
//            if(normalSJ.getMsxz().equals("00")){
//                normalSJ.setSdwd((float) Integer.parseInt(re.substring(18,20),16));
//                normalSJ.setCswd((float) Integer.parseInt(re.substring(176,178),16));
//                normalSJ.setHswd((float) Integer.parseInt(re.substring(174,176),16));
//            } else if(normalSJ.getMsxz().equals("01")) {
//                normalSJ.setSdwd((float) Integer.parseInt(re.substring(26,28),16));
//                normalSJ.setCswd((float) Integer.parseInt(re.substring(234,236),16));
//                normalSJ.setHswd((float) Integer.parseInt(re.substring(190,192),16));
//            } else {
//                normalSJ.setSdwd((float) Integer.parseInt(re.substring(22,24),16));
//                normalSJ.setCswd((float) Integer.parseInt(re.substring(234,236),16));
//                normalSJ.setHswd((float) Integer.parseInt(re.substring(190,192),16));
//            }
//            normalSJ.setKzbz(re.substring(12,14));
//            normalSJ.setGzdm(re.substring(154,156));
//            normalSJ.setJqlx(re.substring(222,224));
//            testMapper.insertAllJqsj(re.substring(12,re.length()-4),jzxx,normalSJ);
//            testMapper.updateJqsj(normalSJ,new Date());
//            testMapper.inserJqsj(normalSJ,jzxx);
//        }

    }

    //更新机器操作记录
    private boolean updateCom(String re){
        String jqbh = null;
        if(re.length()>=10) jqbh = re.substring(0,2);
        if(jqbh!=null&&!jqbh.equals("")&&re!=null&&!re.trim().equals("")) {
            if(xhmss.get(jqbh)!=null&&xhmss.get(jqbh).get(re.substring(2,12))!=null&&
                    xhmss.get(jqbh).get(re.substring(2,12)).getJlzt().equals("0")){
                testMapper.updateJQCZ(re.substring(0,re.length()-4),
                        testMapper.getJqUUID(jzxx.getId(),jqbh),new Date());
                return true;
            }
        }
        return false;
    }

    private void loadJqgz(String jqbh,String xh){
        List<XHMS> li = testMapper.getXhmsByXh(xh);
        HashMap map = new HashMap();
        for(XHMS xhms:li) {
            map.put(xhms.getCzzl(),xhms);
        }
        xhmss.put(jqbh,map);
    }

}
