package spring.battle.xml;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * @author jbaruch
 * @since 10/13/14
 */
public abstract class ParserBase implements Parser {

    private  enum State {
        NOT_INITIALIZED, OPEN, CLOSED
    }

    private State state = State.NOT_INITIALIZED;

    private static final Logger LOG = LoggerFactory.getLogger(ParserBase.class);

    @Override
    public void create() {
        if (state != State.NOT_INITIALIZED) {
            throw new IllegalStateException("Can't create parser, was already used.");
        }
        state = State.OPEN;
    }

    @SuppressWarnings("unchecked")
    protected Map<String, String> map(ObjectMapper mapper, String data) throws IOException {
        if (state != State.OPEN) {
            throw new IllegalStateException("Can't operate on closed or not initialized Parser, get yourself a new one");
        }
        Map map = mapper.readValue(data, Map.class);
        if (map != null) {
            map.put(PARSER_NAME_KEY, toString());
        }
        return map;
    }

    @Override
    public void close() {
        switch (state) {
            case CLOSED:
                throw new IllegalStateException("Can't close already closed parser");
            case NOT_INITIALIZED:
                throw new IllegalStateException("Can't close not initialized parser");
            case OPEN:
                LOG.info("Closing " + toString() + ", no more parsing for you! ");
        }
    }

}
