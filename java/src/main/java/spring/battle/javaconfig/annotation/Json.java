package spring.battle.javaconfig.annotation;

import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import spring.battle.javaconfig.OnJsonCondition;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Configuration
@Conditional(OnJsonCondition.class)
public @interface Json {
}
