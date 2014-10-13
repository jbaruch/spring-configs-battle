package spring.battle.javaconfig;

import java.util.Map;

/**
 * Created by Jeka on 13/10/2014.
 */
public interface Parser {

    String PARSER_NAME_KEY = "parser.name";

    Map<String, String> map(String data);

    void create();

    void close();
}
