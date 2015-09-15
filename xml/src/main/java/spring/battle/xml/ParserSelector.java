package spring.battle.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertiesPropertySource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by igole01 on 9/14/15.
 */
public class ParserSelector implements ApplicationContextInitializer {
    private static Logger LOG = LoggerFactory.getLogger(Cluster.class);

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

        PropertiesPropertySource ps = null;
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("parser.properties"));
            LOG.info("Current parser profile is " + props);
            ps = new PropertiesPropertySource("parser", props);
        } catch (IOException e) {
            e.printStackTrace();
        }
        applicationContext.getEnvironment().getPropertySources().addFirst(ps);
    }
}
