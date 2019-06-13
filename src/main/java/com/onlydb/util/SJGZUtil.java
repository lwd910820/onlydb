package com.onlydb.util;

public class SJGZUtil {

    public static String transMsg(String msg,String find){

        String data = null;

        switch (find){
            case "0":
                data = msgToMsg(msg);
                break;
            case "1":
                data = msg16ToIEEE754(msg);
                break;
            case "2":
                data = msg16ToDouble(msg);
                break;
            case "3":
                data = msg16ToDouble10(msg);
                break;
            case "4":
                data = msg16ToDouble100(msg);
                break;
            case "5":
                data = msgToInteger(msg);
                break;
            case "6":
                data = msgToBanirys(msg);
                break;
        }

        return data;
    }

    private static String msgToMsg(String msg){
        return msg;
    }

    private static String msg16ToIEEE754(String msg){
        Float f = -1f;
        try {
            Integer k = Integer.valueOf(msg,16);
            f = Float.intBitsToFloat(k);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f.toString();
    }

    private static String msg16ToDouble(String msg){
        Double d = null;
        try {
            d = Integer.valueOf(msg,16)/1.0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d.toString();
    }

    private static String msg16ToDouble10(String msg){
        Double d = null;
        try {
            d = Integer.valueOf(msg,16)/10.0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d.toString();
    }

    private static String msg16ToDouble100(String msg){
        Double d = null;
        try {
            d = Integer.valueOf(msg,16)/100.0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d.toString();
    }

    private static String msgToInteger(String msg){

        return Integer.valueOf(msg,16).toString();
    }

//    private static String douToHex(Double num){
//        Integer ms = num.intValue();
//        return Integer.toHexString(ms);
//    }

    private static String msgToBanirys(String msg){
        StringBuffer sb = new StringBuffer();
        int i=msg.length()*4;
        int key = Integer.valueOf(msg,16);
        while (i>0){
            sb.insert(0,key&1) ;
            key>>=1;
            i--;
        }
        return sb.toString();
    }

}
