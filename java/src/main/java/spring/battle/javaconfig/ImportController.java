package spring.battle.javaconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Jeka on 13/10/2014.
 */
@RestController
public class ImportController {

    private Parser parser;
    private Cluster cluster;

    @Autowired
    public ImportController(Parser parser, Cluster cluster) {
        this.parser = parser;
        this.cluster = cluster;
    }

    @RequestMapping(value = "/importData", method = POST, produces = "text/plain")
    public String importData(@RequestBody String body) throws Exception {
        String[] documents = body.split("---");

        Stream<String> results = Arrays.stream(documents)
            .map(document -> {
                parser.create();
                String saveResult = cluster.save(parser.map(document));
                parser.close();
                return saveResult;
            });

        return results.collect(toList()).toString();
    }
}
