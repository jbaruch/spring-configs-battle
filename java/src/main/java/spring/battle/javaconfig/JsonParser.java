package spring.battle.javaconfig;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Jeka on 13/10/2014.
 */
public class JsonParser extends ParserBase {

    private ObjectMapper mapper;

    public JsonParser() {
        mapper = new ObjectMapper();
    }

    @Override
    public Map<String, String> map(String data) {
        try {
            return map(mapper, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
