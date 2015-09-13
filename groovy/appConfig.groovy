import com.hazelcast.config.XmlConfigBuilder
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import org.springframework.beans.factory.FactoryBean
import spring.battle.groovy.Cluster
import spring.battle.groovy.ImportController
import spring.battle.groovy.XmlParser

beans {

    xmlns([mvc: 'http://www.springframework.org/schema/mvc'])
    xmlns([aop: 'http://www.springframework.org/schema/aop'])
    xmlns([context: 'http://www.springframework.org/schema/context'])

    mvc.'annotation-driven'()

    xmlParser XmlParser

    hazelcastInstance(HazelcastInstanceFactoryBean) {
        hazelcastPropertiesFilename = 'hazelcast.properties'
        hazelcastConfigFilename = 'hazelcast.xml'
    }

    cluster(Cluster, hazelcastInstance) {bean ->
        bean.destroyMethod = 'shutdown'
    }

    importController ImportController, xmlParser, cluster
}

class HazelcastInstanceFactoryBean implements FactoryBean<HazelcastInstance> {
    String hazelcastPropertiesFilename
    String hazelcastConfigFilename

    @Override
    HazelcastInstance getObject() throws Exception {

        Properties hzProperties = new Properties()
        File propertiesFile = new File(hazelcastPropertiesFilename)
        propertiesFile.withInputStream {
            hzProperties.load(it)
        }
        def instance
        this.getClass().getResource(hazelcastConfigFilename).withInputStream {
            XmlConfigBuilder  xmlConfigBuilder = new XmlConfigBuilder(it)
            xmlConfigBuilder.properties = hzProperties
            instance = Hazelcast.newHazelcastInstance(xmlConfigBuilder.build())
        }
        instance
    }

    @Override
    Class<?> getObjectType() {
        HazelcastInstance
    }

    @Override
    boolean isSingleton() {
        true
    }
}