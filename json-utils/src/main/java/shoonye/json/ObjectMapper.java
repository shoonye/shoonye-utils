package shoonye.json;

import shoonye.json.flexi.FlexiObjectModule;
import shoonye.json.flexi.FlexiObjectSerializer;

/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 */
public class ObjectMapper extends com.fasterxml.jackson.databind.ObjectMapper {
    private static final long serialVersionUID = 1L;

    public ObjectMapper() {
		super();
		registerModule(new FlexiObjectModule(new FlexiObjectSerializer()));
	}

}
