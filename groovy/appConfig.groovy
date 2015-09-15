import com.hazelcast.config.XmlConfigBuilder
import org.springframework.beans.factory.support.LookupOverride
import org.springframework.beans.factory.support.MethodOverrides
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import org.springframework.beans.factory.FactoryBean
import spring.battle.groovy.Cluster
import spring.battle.groovy.ImportController
import spring.battle.groovy.JsonParser
import spring.battle.groovy.XmlParser

beans {

    xmlns([mvc: 'http://www.springframework.org/schema/mvc'])
    xmlns([aop: 'http://www.springframework.org/schema/aop'])
    xmlns([context: 'http://www.springframework.org/schema/context'])

    mvc.'annotation-driven'()

    switch (System.getenv('SPRING_PROFILES_ACTIVE')) {
        case 'JSON':
            parser(JsonParser).scope = 'prototype'
            break
        case 'XML':
            parser(XmlParser).scope = 'prototype'
            break
        default:
            throw new IllegalStateException('can\'t run without a profile')
    }

    hazelcastInstance(HazelcastInstanceFactoryBean) {
        hazelcastPropertiesFilename = 'hazelcast.properties'
        hazelcastConfigFilename = 'hazelcast.xml'
    }

    cluster(Cluster, hazelcastInstance) {bean ->
        bean.destroyMethod = 'shutdown'
    }

    importController(ImportController, cluster) { bean ->
        bean.methodOverrides = new MethodOverrides()
        bean.methodOverrides.addOverride(new LookupOverride('getParser', 'parser'))
    }
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