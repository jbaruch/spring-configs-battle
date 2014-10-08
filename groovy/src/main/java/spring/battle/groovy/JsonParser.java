package spring.battle.groovy;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * @author jbaruch
 * @since 10/8/14
 */
public class JsonParser implements Parser {

    private ObjectMapper mapper;

    public JsonParser() {
        mapper = new ObjectMapper();
    }

    @Override
    public Map<String, String> map(String data) throws IOException {
        return map(mapper, data);
    }
}
