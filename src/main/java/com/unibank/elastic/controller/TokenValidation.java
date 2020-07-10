package com.unibank.elastic.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibank.elastic.exception.InvalidTokenException;
import com.unibank.elastic.model.Token;
import com.unibank.elastic.service.DecryptToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TokenValidation {
    private  Logger logger = LogManager.getLogger();
   @Value("${token}")
    private  String textToEncrypt ;






    public void validateToken(String decryptedKey, String method) throws InvalidTokenException, JsonProcessingException {
        System.out.println("X-token = "+decryptedKey);
        DecryptToken decryptToken = new DecryptToken();
        String pass = decryptToken.getDecryptedToken(decryptedKey).getPass();
        System.out.println(pass);
        System.out.println(textToEncrypt);
        System.out.println(textToEncrypt.equals(pass));
        if(!pass.equals(textToEncrypt) ){
            logger.error("TOKEN VALIDATION. "+ method );
            throw  new InvalidTokenException();
        }
    }


    public void validatePass(String pass , String method) throws InvalidTokenException {

        System.out.println("Validate "+pass);
        System.out.println(textToEncrypt);
        System.out.println(textToEncrypt.equals(pass));
        if(pass ==null  || !pass.equals(textToEncrypt) ){
            logger.error("TOKEN VALIDATION. "+ method );
            throw  new InvalidTokenException();
        }
    }


//NOT USED
    public void validToken(String token, String method)throws InvalidTokenException
    {



       /*
       * 7f580ac336f7687d
Src: Hello
Cipher: 30d8fbd34da9e8c16a4e92491e9ee8809bd1b9b28a2ad261a9336f6041029cac
Decrypted: Hello
       * */
        String password = "LxGWZAXt0MGNQBwEtknv";
        String textToEncrypt = "Hello";

        // String salt1 = KeyGenerators.string().generateKey();
        String salt = "7f580ac336f7687d";

        TextEncryptor encryptor = Encryptors.text(password,salt);


        String decrypted =encryptor.decrypt(token);

        if(!decrypted.equals(textToEncrypt)) {
            logger.error("TOKEN VALIDATION. "+ method );

            throw  new InvalidTokenException();}

    }

}
