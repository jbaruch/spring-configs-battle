package spring.battle.groovy;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.util.Map;

/**
 * @author jbaruch
 * @since 10/8/14
 */
public class XmlParser implements Parser {
    private XmlMapper xmlParser;

    public XmlParser() {
        xmlParser = new XmlMapper();
    }

    @Override
    public Map<String, String> map(String data) throws IOException {
        return map(xmlParser, data);
    }
}
