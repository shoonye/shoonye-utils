package shoonye.json.flexi;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import shoonye.json.util.FlexiObject;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 */
public class FlexiObjectDeserializer extends StdDeserializer<FlexiObject> {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(FlexiObjectDeserializer.class);
    
    protected FlexiObjectDeserializer() {
		super(FlexiObject.class);
	}

	@Override
	public FlexiObject deserialize(JsonParser parser, DeserializationContext context)
			throws IOException, JsonProcessingException {
	    logger.debug("Deserializer called");
		return new shoonye.json.util.FlexiObject();
	}

}
