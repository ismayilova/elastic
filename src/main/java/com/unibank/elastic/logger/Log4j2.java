package com.unibank.elastic.logger;

import com.unibank.elastic.ElasticApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Log4j2 {
    private  Logger logger = LogManager.getLogger();

    public Logger getLogger() {
        return logger;
    }
}
