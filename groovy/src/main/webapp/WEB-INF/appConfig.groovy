import org.springframework.beans.factory.FactoryBean
import spring.battle.groovy.ClusterStub
import spring.battle.groovy.ImportController
import spring.battle.groovy.JsonParser
import spring.battle.groovy.XmlParser

import static spring.battle.groovy.ClusterStub.Builder.PoolingOptions.Options.LOCAL
import static spring.battle.groovy.ClusterStub.DowngradingConsistencyRetryPolicy.INSTANCE

beans {

    xmlns([mvc: 'http://www.springframework.org/schema/mvc'])
    mvc.'annotation-driven'()

    jsonParser JsonParser
    xmlParser XmlParser

    cluster ClusterFactoryBean

    importController ImportController, xmlParser, cluster
}

class ClusterFactoryBean implements FactoryBean<ClusterStub> {

    @Override
    ClusterStub getObject() throws Exception {
        ClusterStub.builder().addContactPoint("some-node")
                .poolingOptions().setCoreConnectionsPerHost(LOCAL, 43).withRetryPolicy(INSTANCE)
                .withReconnectionPolicy(new ClusterStub.Builder.ConstantReconnectionPolicy(100L))
                .build()
    }

    @Override
    Class<?> getObjectType() {
        ClusterStub
    }

    @Override
    boolean isSingleton() {
        true
    }
}