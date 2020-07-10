package com.unibank.elastic.controller;

import com.unibank.elastic.dal.Dal;
import com.unibank.elastic.exception.InvalidTokenException;
import com.unibank.elastic.model.Response;

import com.unibank.elastic.model.Token;
import com.unibank.elastic.service.DecryptToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/v1")
public class RestControllers {

    private Logger logger = LogManager.getLogger();
   @Autowired
   Dal dal;

    @Autowired
    TokenValidation tokenValidation;

    public RestControllers(Dal dal, TokenValidation tokenValidation) {
        this.dal = dal;
        this.tokenValidation = tokenValidation;
    }







//Inserts document in  unimblog_20200709
@PostMapping("/data")
public Response addDocument( @RequestHeader("X-token") String token,@RequestBody String json) throws InvalidTokenException, IOException {


//    tokenValidation.validateToken(token,"addDocumentInType");
     String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
      System.out.println(date);
    String index = "unimblog_" +  date;
    String type = "_doc";

    Token tokenObject = new DecryptToken().getDecryptedToken(token);
    tokenValidation.validatePass(tokenObject.getPass(),"addDocumentInType");
    boolean res;

    try{
          res = dal.addData(json,index , type);
    }catch (IOException ex){
        logger.error("IOException  "+ ex.getMessage());
    }

    return new Response("addDocumen ",true);
}


    //Inserts document in given index/type
    @PostMapping("/data/{index}")
    public Response addDocumentInIndex( @RequestHeader("X-token") String token,@RequestBody String json ,@PathVariable String index) throws InvalidTokenException, IOException {


//    tokenValidation.validateToken(token,"addDocumentInType");

        String type = "_doc";

        Token tokenObject = new DecryptToken().getDecryptedToken(token);
        tokenValidation.validatePass(tokenObject.getPass(),"addDocumentInType");
        boolean res;

        try{
            res = dal.addData(json,index , type);
        }catch (IOException ex){
            logger.error("IOException  "+ ex.getMessage());
        }

        return new Response("addDocument",true);
    }



//inserts bulk data in unimblog_20200709 ..
@PostMapping("/bulkdata")
public Response addBulk( @RequestHeader("X-token") String token,@RequestBody String data) throws IOException ,InvalidTokenException {
    boolean res = false;

 //   tokenValidation.validToken(token,"addBulk");

    String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
    System.out.println(date);
    String index = "unimblog_" +  date;
    String type = "_doc";




    Token tokenObject = new DecryptToken().getDecryptedToken(token);
    tokenValidation.validatePass(tokenObject.getPass(),"addBulk");




   try{
       System.out.println(tokenObject.getIndex() +" "+ tokenObject.getType());
         res = dal.addBulkData(data,index ,type);
         return new Response("addBulkData",res);
   }catch (IOException ex){
       logger.error("IOException  "+ ex.getMessage());
       System.out.println(ex.getMessage());

   }
    return new Response("addBulkData",false);
}



    //inserts bulk data in given index/type
    @PostMapping("/bulkdata/{index}")
    public Response addBulk( @RequestHeader("X-token") String token,@RequestBody String data , @PathVariable String index) throws IOException ,InvalidTokenException {
        boolean res = false;

        //   tokenValidation.validToken(token,"addBulk");

        String type = "_doc";




        Token tokenObject = new DecryptToken().getDecryptedToken(token);
        tokenValidation.validatePass(tokenObject.getPass(),"addBulk");




        try{
            System.out.println(tokenObject.getIndex() +" "+ tokenObject.getType());
            res = dal.addBulkData(data,index ,type);
            return new Response("addBulkData",res);
        }catch (IOException ex){
            logger.error("IOException  "+ ex.getMessage());
            System.out.println(ex.getMessage());

        }
        return new Response("addBulkData",false);
    }




}





