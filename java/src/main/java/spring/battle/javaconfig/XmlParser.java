package spring.battle.javaconfig;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@Scope(value = "prototype")
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
