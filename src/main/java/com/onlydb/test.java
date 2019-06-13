//package com.onlydb;
//
//import com.onlydb.config.utils.LocalUtils;
//import com.onlydb.data.mac.dao.TestMapper;
//import com.onlydb.util.CRC16Util;
//import com.sun.org.apache.xerces.internal.impl.dv.xs.HexBinaryDV;
//import org.apache.ibatis.io.ResolverUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
////@SpringBootApplication
//public class test implements CommandLineRunner {
//
//    @Autowired
//    private TestMapper mapper;
//
//    public static void main(String[] args) {
//
////        SpringApplication app = new SpringApplication(test.class);
////        LocalUtils.context = app.run(args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println();
//        System.out.println(mapper.getXhms("FCC1531F3F2645FEBE8DBC57B629048A").getXhmsgzs());
//    }
//
//}
