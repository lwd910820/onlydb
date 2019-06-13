package com.onlydb.httpServer.service;

import com.onlydb.data.mac.dao.TestMapper;
import com.onlydb.global.prop.SocketPool;
import com.onlydb.macServer.handler.MessageHandler;
import com.onlydb.util.CRC16Util;
import com.onlydb.util.TransUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.ReferenceCountUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class SingleService extends HttpService {

    @Autowired
    private TestMapper testMapper;

    public SingleService(String url) {
        super(url);
    }

    @Override
    public void resolveUrl() {
        if(!testMapper.getRK().contains(param.get("rk"))){
            send(ctx,"非法请求", HttpResponseStatus.OK);
            return;
        } else {
            String jzbh = (String) param.get("jzid");
            String jqbh = (String) param.get("jqid");
            String zdyzl = (String) param.get("zdyzl");
            String jqcs = (String) param.get("param");
            if(jzbh==null||jqbh==null) {
                send(ctx,"请求参数错误",HttpResponseStatus.OK);
                return;
            } else {
                Map<String,String> address = testMapper.getJzAddr(jzbh);
                if(address==null) {
                    send(ctx,"机组未连接",HttpResponseStatus.OK);
                    return;
                }
                String ip = address.get("JZIP")+":"+address.get("JZPORT");
                MessageHandler channel = SocketPool.getValidChannel(ip);
                if(TransUtil.state.get(ip)==null){
                    send(ctx,"机器未连接",HttpResponseStatus.OK);
                    return;
                }
                if(TransUtil.state.get(ip)!=0){
                    send(ctx,"当前机器正在被操作，请稍后",HttpResponseStatus.OK);
                    return;
                }
                ByteBuf buf = Unpooled.buffer();
                operation(ip,jqbh,zdyzl,jqcs,buf,channel.getCtx());
                return;
            }
        }
    }

    private String getZlj(String jqbh, String jqcs, String jqzl){
        if(jqzl!=null&&!jqzl.equals("")){
            return jqbh+jqzl.toUpperCase();
        } else {
            return null;
        }
    }

    private void operation(String ip,String jqbh,String zdyzl,String jqcs,ByteBuf buf,ChannelHandlerContext channel){
        String zl = getZlj(jqbh,jqcs,zdyzl);
        if(zl==null) {
            if(buf.refCnt()>0) ReferenceCountUtil.release(buf);
            return;
        }
        TransUtil.state.put(ip,1);
        TransUtil.zls.put(ip,zl);
        buf.writeBytes(CRC16Util.getSendBuf(zl));
        channel.writeAndFlush(buf).addListener(future -> {
            if(future.isSuccess()){

            }
        });
        int i = 0;
        while (!TransUtil.state.get(ip).equals(0)&&i<5){
            try {
                Thread.sleep(1000);
                i++;
            } catch (Exception e) {
                send(ctx,"系统请求异常",HttpResponseStatus.OK);
                TransUtil.state.put(ip,0);
                return;
            }
        }
        if(TransUtil.state.get(ip).equals(0)) send(ctx,TransUtil.mes.get(ip),HttpResponseStatus.OK);
        else {
            send(ctx,"请求超时",HttpResponseStatus.OK);
            if(TransUtil.state.get(ip).equals(1)) TransUtil.state.put(ip,0);
        }
        return;
    }

}
