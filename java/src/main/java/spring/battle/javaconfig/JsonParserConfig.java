package spring.battle.javaconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("json")
public class JsonParserConfig extends AppConfig {
    @Override
    public Parser parser() {
        return new JsonParser();
    }
}
