package spring.battle.xml;

import org.springframework.beans.factory.FactoryBean;

import static spring.battle.xml.ClusterStub.Builder.PoolingOptions.Options.LOCAL;
import static spring.battle.xml.ClusterStub.DowngradingConsistencyRetryPolicy.INSTANCE;

/**
 * @author jbaruch
 * @since 10/9/14
 */
public class ClusterFactoryBean implements FactoryBean<ClusterStub> {

    @Override
    public ClusterStub getObject() throws Exception {
        return ClusterStub.builder().addContactPoint("some-node")
                .poolingOptions().setCoreConnectionsPerHost(LOCAL, 43).withRetryPolicy(INSTANCE)
                .withReconnectionPolicy(new ClusterStub.Builder.ConstantReconnectionPolicy(100L))
                .build();
    }

    @Override
    public Class<?> getObjectType() {
        return ClusterStub.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
