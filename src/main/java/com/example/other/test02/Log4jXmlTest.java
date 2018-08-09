package com.example.other.test02;

import org.apache.log4j.Logger;

public class Log4jXmlTest {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("error");
        logger.debug("debug");
        logger.info("info");
        //logger.error("error啦{}");
        try {
            int a = 1/0;
        }catch (Exception e){
            logger.error("出错了",e);
        }

    }
}
