package spring.battle.javaconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("xml")
public class XmlPargerConfig extends AppConfig {
    @Override public Parser parser() {
        return new XmlParser();
    }
}
