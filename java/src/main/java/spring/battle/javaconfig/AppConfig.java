package spring.battle.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static spring.battle.javaconfig.Cluster.Builder.PoolingOptions.Options.LOCAL;
import static spring.battle.javaconfig.Cluster.DowngradingConsistencyRetryPolicy.INSTANCE;

/**
 * Created by Jeka on 13/10/2014.
 */
@Configuration
@ComponentScan
@EnableWebMvc
public class AppConfig {
    @Bean
    public Cluster cluster() {
        return Cluster.builder().addContactPoint("some-node")
                .poolingOptions().setCoreConnectionsPerHost(LOCAL, 43).withRetryPolicy(INSTANCE)
                .withReconnectionPolicy(new Cluster.Builder.ConstantReconnectionPolicy(100L))
                .build();
    }
}
