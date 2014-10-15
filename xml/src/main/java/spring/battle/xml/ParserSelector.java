package spring.battle.xml;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author jbaruch
 * @since 10/15/14
 */
public class ParserSelector implements ApplicationContextInitializer<ConfigurableWebApplicationContext> {

    @Override
    public void initialize(ConfigurableWebApplicationContext applicationContext) {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("parser.properties"));
            PropertiesPropertySource ps = new PropertiesPropertySource("parser", props);
            applicationContext.getEnvironment().getPropertySources().addFirst(ps);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
