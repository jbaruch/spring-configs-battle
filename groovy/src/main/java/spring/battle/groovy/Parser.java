package spring.battle.groovy;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * @author jbaruch
 * @since 10/8/14
 */
public interface Parser {

    String PARSER_NAME_KEY = "parser.name";

    Map<String, String> map(String data) throws IOException;

    @SuppressWarnings("unchecked")
    default Map<String, String> map(ObjectMapper mapper, String data) throws IOException {
        Map map = mapper.readValue(data, Map.class);
        if (map != null) {
            map.put(PARSER_NAME_KEY, this.getClass().getSimpleName());
        }
        return map;
    }


}
