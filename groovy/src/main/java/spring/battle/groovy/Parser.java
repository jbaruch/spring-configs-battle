package spring.battle.groovy;

import java.io.IOException;
import java.util.Map;

/**
 * @author jbaruch
 * @since 10/8/14
 */
public interface Parser {
    Map<String, String> map(String data) throws IOException;
}
