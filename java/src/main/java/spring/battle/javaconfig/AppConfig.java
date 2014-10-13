package spring.battle.javaconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static spring.battle.javaconfig.Cluster.Builder.PoolingOptions.Options.LOCAL;
import static spring.battle.javaconfig.Cluster.DowngradingConsistencyRetryPolicy.INSTANCE;

/**
 * Created by Jeka on 13/10/2014.
 */
@Configuration
@ComponentScan
@EnableWebMvc
@PropertySource("file:cassandra.properties")
public class AppConfig {


    @Bean
    @Autowired
    public Cluster cluster(@Value("${contactPoint}") String contactPoint,
                           @Value("${connectionsPerHost}") int connectionsPerHost,
                           @Value("${reconnectionPolicy}") long reconnectionPolicy) {
        return Cluster.builder().addContactPoint(contactPoint)
                .poolingOptions().setCoreConnectionsPerHost(LOCAL, connectionsPerHost).withRetryPolicy(INSTANCE)
                .withReconnectionPolicy(new Cluster.Builder.ConstantReconnectionPolicy(reconnectionPolicy))
                .build();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
