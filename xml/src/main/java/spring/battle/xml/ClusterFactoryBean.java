package spring.battle.xml;

import org.springframework.beans.factory.FactoryBean;

import static spring.battle.xml.Cluster.Builder.PoolingOptions.Options.LOCAL;
import static spring.battle.xml.Cluster.DowngradingConsistencyRetryPolicy.INSTANCE;

/**
 * @author jbaruch
 * @since 10/9/14
 */
public class ClusterFactoryBean implements FactoryBean<Cluster> {

    @Override
    public Cluster getObject() throws Exception {
        return Cluster.builder().addContactPoint("some-node")
                .poolingOptions().setCoreConnectionsPerHost(LOCAL, 43).withRetryPolicy(INSTANCE)
                .withReconnectionPolicy(new Cluster.Builder.ConstantReconnectionPolicy(100L))
                .build();
    }

    @Override
    public Class<?> getObjectType() {
        return Cluster.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
