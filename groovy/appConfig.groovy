import org.springframework.beans.factory.FactoryBean
import spring.battle.groovy.Cluster
import spring.battle.groovy.ImportController
import spring.battle.groovy.XmlParser

import static spring.battle.groovy.Cluster.Builder.PoolingOptions.Options.LOCAL
import static spring.battle.groovy.Cluster.DowngradingConsistencyRetryPolicy.INSTANCE

beans {

    xmlns([mvc: 'http://www.springframework.org/schema/mvc'])
    xmlns([aop: 'http://www.springframework.org/schema/aop'])
    xmlns([context: 'http://www.springframework.org/schema/context'])

    mvc.'annotation-driven'()

    context.'property-placeholder'(location: 'file:cassandra.properties')

    xmlParser XmlParser

    cluster (ClusterFactoryBean) {
        contactPoint = '${contactPoint}'
        connectionsPerHost = '${connectionsPerHost}'
        reconnectionPolicy = '${reconnectionPolicy}'
    }

    importController ImportController, xmlParser, cluster
}

class ClusterFactoryBean implements FactoryBean<Cluster> {
    String contactPoint
    int connectionsPerHost
    long reconnectionPolicy

    @Override
    Cluster getObject() throws Exception {
        Cluster.builder().addContactPoint(contactPoint)
                .poolingOptions().setCoreConnectionsPerHost(LOCAL, connectionsPerHost).withRetryPolicy(INSTANCE)
                .withReconnectionPolicy(new Cluster.Builder.ConstantReconnectionPolicy(reconnectionPolicy))
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