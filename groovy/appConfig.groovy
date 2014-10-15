import org.springframework.beans.factory.FactoryBean
import org.springframework.beans.factory.support.LookupOverride
import org.springframework.beans.factory.support.MethodOverrides
import spring.battle.groovy.Cluster
import spring.battle.groovy.ImportController

import static spring.battle.groovy.Cluster.Builder.PoolingOptions.Options.LOCAL
import static spring.battle.groovy.Cluster.DowngradingConsistencyRetryPolicy.INSTANCE

beans {

    xmlns([mvc: 'http://www.springframework.org/schema/mvc'])
    xmlns([aop: 'http://www.springframework.org/schema/aop'])
    xmlns([context: 'http://www.springframework.org/schema/context'])
    xmlns([lang: 'http://www.springframework.org/schema/lang'])

    mvc.'annotation-driven'()

    context.'property-placeholder'(location: 'file:cassandra.properties')

    Properties parserProps = new Properties()
    new File('parser.properties').withInputStream { is ->
        parserProps.load(is)
    }

    lang.groovy(id: 'parser', 'refresh-check-delay': 1000, scope: 'prototype',
            'script-source': "file:${parserProps.parserImpl}Parser.groovy")

    cluster(ClusterFactoryBean) {
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