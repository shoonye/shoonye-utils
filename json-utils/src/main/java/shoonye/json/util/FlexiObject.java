package shoonye.json.util;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.DateTime;

import shoonye.util.ClassUtil;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Anuradha Chowdhary 
 * @author anuradha@shoonye.com
 * 
 */
public class FlexiObject {   
    protected String _id;
	protected DateTime createdDate = new DateTime();
	protected Map<String, Object> properties;

	@JsonProperty("_id")
	public String getId() {
		return _id;
	}
	
	public void setId(String id) {
		this._id = id;
	}
	
    public DateTime getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    @JsonAnySetter
	public void setGenericProperties(String key, Object value) {
		set(key,value);
	}
	
	public Object $(String property){
		Object value = get(property);
		if(value==null){
			return ClassUtil.fieldValue(this, property);
		}
		return value;
	}

	public Object get(String property) {
		if(properties==null) return null;
		return properties.get(property);
	}
	
	public void $(String propertyName, Object value){
		Map<String, Field> decaleredFields = ClassUtil.allFieldsMap(getClass());
		Field field = decaleredFields.get(propertyName);
		if(field==null){
			set(propertyName, value);
		}else{
			if(propertyName.equalsIgnoreCase("properties")) 
				throw new IllegalArgumentException();
			try {
				setFieldValue(this, field, value);
			} catch (Exception e) {
				set(propertyName, value);
			}
		}		
	}

	public void set(String propertyName, Object value) {
		if(properties==null) properties = new HashMap<String, Object>();
		properties.put(propertyName, value);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlexiObject other = (FlexiObject) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}
	
	//Powerfull method to trafrom on FlexiObject to other, but need to be used judiciously
	public <T extends FlexiObject> T to(Class<T> clazz) throws Exception{
		T otherObject = (T) clazz.newInstance();
		Collection<Field> myFields = ClassUtil.allFields(getClass());
		Map<String, Field> otherFields = ClassUtil.allFieldsMap(clazz);

		for (Field myField : myFields) {
			if (myField.getName().equalsIgnoreCase("properties"))
				continue;
			Object myValue = ClassUtil.fieldValue(this, myField);			
			Field otherField = otherFields.get(myField.getName());
			
			if (otherField != null) {
				setFieldValue(otherObject,otherField,myValue);
			} else {
				otherObject.set(myField.getName(), myValue);
			}
		}

		if (properties != null) {
			for (Entry<String, Object> prop : properties.entrySet()) {
				Field otherField = otherFields.get(prop.getKey());
				if (otherField != null) {
					setFieldValue(otherObject,otherField,prop.getValue());
				} else {
					otherObject.set(prop.getKey(), prop.getValue());
				}
			}
		}
		return otherObject;		
	}
	
	public void merge(FlexiObject other) throws Exception{
		Collection<Field> otherFields = ClassUtil.allFields(other.getClass());
		Map<String, Field> myFields = ClassUtil.allFieldsMap(getClass());

		for (Field field : otherFields) {
			if (field.getName().equalsIgnoreCase("properties"))
				continue;
			Field myField = myFields.get(field.getName());
			Object otherValue = ClassUtil.fieldValue(other, field);	
			
			if (myField != null) {
				setFieldValue(this,myField,otherValue);
			} else {
				set(field.getName(), otherValue);
			}
		}

		if (other.properties != null) {
			for (Entry<String, Object> prop : other.properties.entrySet()) {
				Field myField = myFields.get(prop.getKey());
				if (myField != null) {
					setFieldValue(this,myField,prop.getValue());
				} else {
					set(prop.getKey(), prop.getValue());
				}
			}
		}
	}
	
	private void setFieldValue(Object object, Field field, Object value) throws Exception{
		boolean originalAccess = field.isAccessible();
		field.setAccessible(true);
		field.set(object, value);
		field.setAccessible(originalAccess);
	}
}
