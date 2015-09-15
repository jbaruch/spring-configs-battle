package spring.battle.javaconfig;

import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@ComponentScan
@EnableWebMvc
public class AppConfig {

    @Bean
    public Cluster cluster() {
        return new Cluster(hazelcast());
    }

    @Bean
    public HazelcastInstance hazelcast() {
        final InputStream inputStream =
            AppConfig.class.getClassLoader().getResourceAsStream("hazelcast.xml");
        final XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder(inputStream);
        xmlConfigBuilder.setProperties(hzProperties());
        Config config = xmlConfigBuilder.build();

        // to test in-process 2 nodes cluster uncomment following line
        // Hazelcast.newHazelcastInstance(config);
        return Hazelcast.newHazelcastInstance(config);
    }

    @Bean
    public Properties hzProperties() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new FileSystemResource("hazelcast.properties"));
        Properties properties = null;
        try {
            propertiesFactoryBean.afterPropertiesSet();
            properties = propertiesFactoryBean.getObject();

        } catch (IOException e) {
            // not cool !!!
        }
        return properties;
    }

    @Bean
    @Scope("prototype")
    public Parser parser() {
        return new JsonParser();
    }

    @Bean
    public ImportController importController() {
        return new ImportController(cluster()) {
            @Override public Parser getParser() {
                return parser();
            }
        };
    }
}
