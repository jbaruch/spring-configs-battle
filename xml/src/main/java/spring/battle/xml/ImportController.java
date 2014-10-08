package spring.battle.xml;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author jbaruch
 * @since 10/8/14
 */
@RestController
public class ImportController {

    private Parser parser;
    private ClusterStub cluster;

    public ImportController(Parser parser, ClusterStub cluster) {
        this.parser = parser;
        this.cluster = cluster;
    }

    @RequestMapping(value = "/importData", method = POST, produces = "text/plain")
    public String importData(@RequestBody String body) throws IOException {
        return cluster.save(parser.map(body));
    }
}
