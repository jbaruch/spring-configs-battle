package spring.battle.groovy;

import java.util.Map;

/**
 * @author jbaruch
 * @since 10/8/14
 */
public interface Parser {

    String PARSER_NAME_KEY = "parser.name";

    Map<String, String> map(String data);

    void create();

    void close();
}
