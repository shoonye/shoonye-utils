package shoonye.json;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 */
public class JSONUtil {

    public static <T> String jsonify(T obj) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(obj);
        } catch(JsonProcessingException e) {
            System.err.println("error deserializing object " + obj.toString());
        }
        return jsonString;
    }
    
}
