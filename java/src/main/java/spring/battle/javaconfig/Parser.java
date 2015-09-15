package spring.battle.javaconfig;

import java.util.Map;

public interface Parser {

    String PARSER_NAME_KEY = "parser.name";

    Map<String, String> map(String data);

    void create();

    void close();
}
