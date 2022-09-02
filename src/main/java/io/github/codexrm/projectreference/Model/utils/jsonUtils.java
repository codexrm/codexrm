package io.github.codexrm.projectreference.model.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;

import java.util.List;

public class JsonUtils {

    private JsonUtils() {}

    // covert JSON into List of Object
    public static  <T> List<T> convertFromJsonToList(String json, TypeReference<List<T>> elem){
        if(json.equals("")){
            return Collections.emptyList();
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json,elem);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    //Generic Type Safe Method - convert JSON into Object
    public static <T> T convertFromJsonToObject(String json, Class<T> elem){
          if(json.equals("")){
              return null;
          }
           ObjectMapper mapper = new ObjectMapper();
           try {
               return mapper.readValue(json, elem);//Convert Json into object of Specific Type
           } catch (JsonProcessingException e) {
               e.printStackTrace();
           }
           return null;
       }

    //convert Object into JSON
    public static String convertFromObjectToJson(Object obj){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
