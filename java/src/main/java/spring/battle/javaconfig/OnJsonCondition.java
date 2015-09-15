package spring.battle.javaconfig;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by vikgamov on 9/15/15.
 */
public class OnJsonCondition implements Condition {

    @Override public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new FileSystemResource("parser.properties"));
        Properties properties = null;
        try {
            propertiesFactoryBean.afterPropertiesSet();
            properties = propertiesFactoryBean.getObject();
            if (properties.get("parserImpl").equals("json")) {
                return true;
            }

        } catch (IOException e) {
            // not cool !!!
        }

        return false;
    }
}
