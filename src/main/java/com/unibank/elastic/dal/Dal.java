package com.unibank.elastic.dal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.ActionRequestValidationException;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import com.unibank.elastic.config.ClientConfig;
import  org.json.JSONArray;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;

@Repository

public class Dal {
    //elastic search location
    private Logger logger = LogManager.getLogger();
    private RestHighLevelClient client;

    public Dal(@Qualifier("clientConfig") RestHighLevelClient client) {
        this.client = client;
    }




    //Adds new document in type and index
    public boolean addData(String json, String index, String type) throws IOException {
    boolean res =false ;




        try {

            IndexRequest indexRequest = new IndexRequest(index).type(type).source(json, XContentType.JSON);

            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);


           res = true;
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }

        return res;
    }



    //BULK request

    public boolean addBulkData(String json, String index, String type) throws IOException {


        BulkRequest request = new BulkRequest();
      try{
          JSONArray jsonArray = new JSONArray(json);

          for (int i=0;i<jsonArray.length();i++) {
              JSONObject jsonObject = jsonArray.getJSONObject(i);
              String js = jsonObject.toString();

              request.add(new IndexRequest(index).type(type).source(js, XContentType.JSON));
          }

      }catch (JSONException jsEx ){
          logger.error(jsEx.getMessage());
          return false;
      }catch (ActionRequestValidationException ex){
          logger.error(ex.getMessage());
          return false;
      }

try {
    BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
} catch (Exception ex){
    System.out.println(ex.getMessage());
    logger.error(ex.getMessage());
    return false;
}
        return true;
    }



}
