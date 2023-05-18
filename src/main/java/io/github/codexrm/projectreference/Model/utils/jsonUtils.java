package io.github.codexrm.projectreference.model.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.codexrm.projectreference.model.dto.UserDTO;

import java.util.List;

public class JsonUtils {

    // covert JSON into List of Object
    static public <T> List<T> convertFromJsonToList(String json, TypeReference<List<T>> var){
        if(json.equals("")){
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json,var);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Generic Type Safe Method - convert JSON into Object
    static public <T> T convertFromJsonToObject(String json, TypeReference<T> var){
        if(json.equals("")){
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            return (T) mapper.readValue(json, var);//Convert Json into object of Specific Type
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
