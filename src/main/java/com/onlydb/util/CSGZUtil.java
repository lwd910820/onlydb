package com.onlydb.util;

public class CSGZUtil {

    public static String transMsg(String msg,String find,Integer len){

        String data = null;

        switch (find){
            case "0":
                strToStr(msg,len);
                break;
            case "1":
                numToHex(msg,len);
                break;
            case "2":
                numTo10Hex(msg,len);
                break;
        }

        return data;
    }

    private static String strToStr(String msg,Integer len){
        StringBuffer k = new StringBuffer(msg);
        return supplyStr(k,len).toString();
    }

    private static String numToHex(String msg,Integer len){
        try {
            Integer num = Integer.parseInt(msg);
            StringBuffer k = new StringBuffer(Integer.toHexString(num));
            return supplyStr(k,len).toString();
        } catch (Exception e){
            return null;
        }
    }

    private static String numTo10Hex(String msg,Integer len){
        try {
            Integer num = Integer.parseInt(msg);
            StringBuffer k = new StringBuffer(Integer.toHexString(num*10));
            return supplyStr(k,len).toString();
        } catch (Exception e){
            return null;
        }
    }

    private static StringBuffer supplyStr(StringBuffer sb,Integer len){
        while (sb.length()<len){
            sb.insert(0,"0");
        }
        return sb;
    }

}
