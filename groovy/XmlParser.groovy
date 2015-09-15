import com.fasterxml.jackson.dataformat.xml.XmlMapper
import groovy.util.logging.Slf4j
import spring.battle.groovy.ParserBase

/**
 * @author jbaruch
 * @since 10/8/14
 */
@Slf4j
class XmlParser extends ParserBase {
    private XmlMapper xmlParser

    public XmlParser() {
        xmlParser = new XmlMapper()
    }

    @Override
    Map<String, String> map(String data) {
        log.info('Reloaded, bitches!')
        map(xmlParser, data)
    }
}
