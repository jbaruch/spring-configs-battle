package spring.battle.groovy;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author jbaruch
 * @since 10/8/14
 */
@RestController
public abstract class ImportController {

    private Cluster cluster;

    public ImportController(Cluster cluster) {
        this.cluster = cluster;
    }

    @RequestMapping(value = "/importData", method = POST, produces = "text/plain")
    public String importData(@RequestBody String body) throws Exception {
        String[] documents = body.split("---");

        Stream<String> results = Arrays.stream(documents)
                .map(document -> {
                    Parser parser = getParser();
                    parser.create();
                    String saveResult = cluster.save(parser.map(document));
                    parser.close();
                    return saveResult;
                });

        return results.collect(toList()).toString();
    }

    public abstract Parser getParser();
}
