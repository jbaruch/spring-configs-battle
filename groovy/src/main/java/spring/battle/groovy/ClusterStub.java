package spring.battle.groovy;

/**
 * @author jbaruch
 * @since 10/8/14
 */
public class ClusterStub {

    public static enum DowngradingConsistencyRetryPolicy {INSTANCE}

    public Builder builder() {
        return new Builder();
    }

    public static class Builder {

        public Builder addContactPoint(String contactPoint) {
            return this;
        }

        public Builder withRetryPolicy(DowngradingConsistencyRetryPolicy policy) {
            return this;
        }

        public Builder withReconnectionPolicy(ConstantReconnectionPolicy policy) {
            return this;
        }

        public ClusterStub build() {
            return new ClusterStub();
        }

        public Builder.PoolingOptions poolingOptions() {
            return new PoolingOptions();
        }

        public static class PoolingOptions {

            public static enum Options {LOCAL}

            public void setCoreConnectionsPerHost(Options option, int value) {

            }

            public int getMaxConnectionsPerHost(Options option) {
                return 100;
            }
        }

        public static class ConstantReconnectionPolicy {
            public ConstantReconnectionPolicy(long timeout) {
            }
        }
    }
}
