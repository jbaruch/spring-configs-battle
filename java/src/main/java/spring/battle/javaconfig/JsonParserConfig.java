package spring.battle.javaconfig;

import spring.battle.javaconfig.annotation.Json;

@Json
public class JsonParserConfig extends AppConfig {
    @Override
    public Parser parser() {
        return new JsonParser();
    }
}
