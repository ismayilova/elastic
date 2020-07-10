package com.unibank.elastic.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibank.elastic.model.Token;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class DecryptToken {
    private Logger logger = LogManager.getLogger();
    public Token getDecryptedToken(String decryptedKey) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        Token token = new Token();
     try {
         ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8081/api/decrypt", decryptedKey, String.class);
          token = new ObjectMapper().readValue(responseEntity.getBody().toString(), Token.class);
     }catch (Exception ex){
         logger.error(ex.getMessage());
         return token;
     }
     return  token;
    }
}
