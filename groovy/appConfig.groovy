import org.springframework.beans.factory.FactoryBean
import spring.battle.groovy.Cluster
import spring.battle.groovy.ImportController
import spring.battle.groovy.JsonParser
import spring.battle.groovy.XmlParser

import static spring.battle.groovy.Cluster.Builder.PoolingOptions.Options.LOCAL
import static spring.battle.groovy.Cluster.DowngradingConsistencyRetryPolicy.INSTANCE

beans {

    xmlns([mvc: 'http://www.springframework.org/schema/mvc'])
    mvc.'annotation-driven'()

    jsonParser JsonParser
    xmlParser XmlParser

    cluster ClusterFactoryBean

    importController ImportController, xmlParser, cluster
}

class ClusterFactoryBean implements FactoryBean<Cluster> {

    @Override
    Cluster getObject() throws Exception {
        Cluster.builder().addContactPoint("some-node")
                .poolingOptions().setCoreConnectionsPerHost(LOCAL, 43).withRetryPolicy(INSTANCE)
                .withReconnectionPolicy(new Cluster.Builder.ConstantReconnectionPolicy(100L))
                .build()
    }

    @Override
    Class<?> getObjectType() {
        Cluster
    }

    @Override
    boolean isSingleton() {
        true
    }
}