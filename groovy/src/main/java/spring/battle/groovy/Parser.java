package spring.battle.groovy;

import java.util.Map;

/**
 * @author jbaruch
 * @since 15/09/2015
 */
public interface Parser {

    String PARSER_NAME_KEY = "parser.name";

    Map<String, String> map(String data);

    void create();

    void close();
}
