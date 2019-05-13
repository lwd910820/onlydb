package com.onlydb;

import com.onlydb.util.CRC16Util;
import com.sun.org.apache.xerces.internal.impl.dv.xs.HexBinaryDV;

public class test {

    public static void main(String[] args) {
        System.out.println(Float.intBitsToFloat(Integer.valueOf("4015C28F",16)));
//        System.out.println(Integer.toHexString(Float.floatToIntBits(0.51f)));
        String strHex = Integer.toHexString(279);
        System.out.println(strHex);
    }

}
