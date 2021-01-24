package shoonye.json.flexi;

import shoonye.json.util.FlexiObject;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 */
public class FlexiObjectModule extends SimpleModule{

    private static final long serialVersionUID = 1L;

    public FlexiObjectModule(FlexiObjectSerializer serializer){
        this(serializer, null);
    }
    public FlexiObjectModule(FlexiObjectSerializer serializer, FlexiObjectDeserializer deserializer) {
		super("FlexiObjectModule", new Version(1, 0, 0, "0", "", ""));
		addSerializer(FlexiObject.class, serializer);	
		//addDeserializer(FlexiObject.class, new FlexiObjectDeserializer());
		//addAbstractTypeMapping(FlexiObject.class, MyFlexiObject.class);
		
	}

}
