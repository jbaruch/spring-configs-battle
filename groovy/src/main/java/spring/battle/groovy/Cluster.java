package spring.battle.groovy;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spring.battle.groovy.Parser;

import java.util.Map;

/**
 * @author jbaruch
 * @since 15/09/2015
 */
public class Cluster {

    private static Logger LOG = LoggerFactory.getLogger(Cluster.class);
    private final HazelcastInstance hazelcastInstance;
    private final IMap<String, String> storage;

    public Cluster(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
        this.storage = hazelcastInstance.getMap("storage");
    }

    public String save(Map<String, String> map) {
        LOG.info("saving " + map);

        map.forEach(storage::put);

        return "Saved " + (map.size() - 1) + " entries, which were parsed by " + map
                .get(Parser.PARSER_NAME_KEY);
    }

    public void shutdown(){
        hazelcastInstance.shutdown();
    }
}
