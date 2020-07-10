package com.unibank.elastic;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibank.elastic.model.Token;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class ElasticApplication {

    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws JsonProcessingException {


      SpringApplication.run(ElasticApplication.class, args);

   }



}
