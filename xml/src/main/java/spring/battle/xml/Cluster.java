package spring.battle.xml;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spring.battle.xml.Parser;

import java.util.Map;

/**
 * Created by Jeka on 13/10/2014.
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

    public void shutdown() {
        LOG.info("Shutdown called");
        hazelcastInstance.shutdown();
    }
}
