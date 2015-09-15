import com.fasterxml.jackson.databind.ObjectMapper
import groovy.util.logging.Slf4j
import spring.battle.groovy.ParserBase

/**
 * @author jbaruch
 * @since 10/8/14
 */
@Slf4j
public class JsonParser extends ParserBase {

    private ObjectMapper mapper

    JsonParser() {
        mapper = new ObjectMapper();
    }

    @Override
    Map<String, String> map(String data) {
        map(mapper, data);
    }
}
