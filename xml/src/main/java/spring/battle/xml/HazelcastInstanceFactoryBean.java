package spring.battle.xml;

import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.FactoryBean;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by igole01 on 9/14/15.
 */
public class HazelcastInstanceFactoryBean implements FactoryBean<HazelcastInstance>{

    private Properties hzProps;
    private String hzConfig;

    public HazelcastInstance getObject() throws Exception {
        final InputStream inputStream =
                HazelcastInstanceFactoryBean.class.getClassLoader().getResourceAsStream(hzConfig);
        final XmlConfigBuilder xmlConfigBuilder = new XmlConfigBuilder(inputStream);
        xmlConfigBuilder.setProperties(hzProps);
        Config config = xmlConfigBuilder.build();

        // to test in-process 2 nodes cluster uncomment following line
        // Hazelcast.newHazelcastInstance(config);
        return Hazelcast.newHazelcastInstance(config);
    }


    @Override
    public Class<?> getObjectType() {
        return HazelcastInstance.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setHzProps(Properties hzProps) {
        this.hzProps = hzProps;
    }

    public void setHzConfig(String hzConfig) {
        this.hzConfig = hzConfig;
    }

}
