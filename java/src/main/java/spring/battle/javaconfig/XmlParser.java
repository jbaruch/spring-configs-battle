package spring.battle.javaconfig;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author jbaruch
 * @since 10/8/14
 */
@Component
public class XmlParser extends ParserBase {
    private XmlMapper xmlParser;

    public XmlParser() {
        xmlParser = new XmlMapper();
    }

    @Override
    public Map<String, String> map(String data) {
        try {
            return map(xmlParser, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
