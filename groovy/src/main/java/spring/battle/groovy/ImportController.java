package spring.battle.groovy;

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

    public ImportController(Parser parser) {
        this.parser = parser;
    }

    @RequestMapping(value = "/importData", method = POST, produces = "text/plain")
    public String importData(@RequestBody String body) throws IOException {
        return "Data imported: " + parser.map(body);
    }
}
