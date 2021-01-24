package shoonye.json.flexi;

import static shoonye.util.ClassUtil.allFields;
import static shoonye.util.ClassUtil.fieldValue;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import shoonye.json.util.FlexiObject;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 */
public class FlexiObjectSerializer extends StdSerializer<FlexiObject> {

	public FlexiObjectSerializer() {
		super(FlexiObject.class);
	}

	@Override
	public void serialize(FlexiObject object, JsonGenerator generator,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		Collection<Field> fields = allFields(object.getClass());
		Field propertiesField = null;
		Map<String, Object> fieldMap = new LinkedHashMap<String, Object>();
		for(Field field : fields){
			Object value = fieldValue(object,field);
			if(field.getName().equalsIgnoreCase("properties")){				
				propertiesField = field;
			} else if (field.getName().equals("_id")) {
			    if(value!=null){
			        fieldMap.put(field.getName(), decorateIdFieldValue(value));
			    }			    
			} else {
				fieldMap.put(field.getName(), value);
			}
		}
		
		@SuppressWarnings("unchecked")
		Map<String,Object> map = (Map<String,Object>) fieldValue(object,propertiesField);
		if(map!=null){
			for (Entry<String, Object> entry : map.entrySet()){
				String key = entry.getKey();
				Object value = entry.getValue();
				if(value!=null){
					if(fieldMap.keySet().contains(key)){
						//log warning
					}else{
						fieldMap.put(key,value);
					}	
				}
				
			}
		}
		generator.writeObject(fieldMap);	
	}

    protected Object decorateIdFieldValue(Object value) {
        return value;
    }

}
