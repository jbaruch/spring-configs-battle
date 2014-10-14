import org.springframework.beans.factory.FactoryBean
import org.springframework.beans.factory.support.LookupOverride
import org.springframework.beans.factory.support.MethodOverrides
import spring.battle.groovy.Cluster
import spring.battle.groovy.ImportController
import spring.battle.groovy.JsonParser

import static spring.battle.groovy.Cluster.Builder.PoolingOptions.Options.LOCAL
import static spring.battle.groovy.Cluster.DowngradingConsistencyRetryPolicy.INSTANCE

beans {

    xmlns([mvc: 'http://www.springframework.org/schema/mvc'])
    xmlns([aop: 'http://www.springframework.org/schema/aop'])
    xmlns([context: 'http://www.springframework.org/schema/context'])

    mvc.'annotation-driven'()

    context.'property-placeholder'(location: 'file:cassandra.properties')

    parser(JsonParser).scope = 'prototype'

    cluster (ClusterFactoryBean) {
        contactPoint = '${contactPoint}'
        connectionsPerHost = '${connectionsPerHost}'
        reconnectionPolicy = '${reconnectionPolicy}'
    }

    importController(ImportController, cluster) { bean ->
        bean.methodOverrides = new MethodOverrides()
        bean.methodOverrides.addOverride(new LookupOverride('getParser', 'parser'))
    }
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