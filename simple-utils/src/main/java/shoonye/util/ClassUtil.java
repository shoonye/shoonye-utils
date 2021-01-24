package shoonye.util;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import shoonye.util.bean.AbstractSearch;
/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 */
public class ClassUtil {
	public static Collection<Field> allFields(Class<?> clazz){
		return allFieldsMap(clazz).values();
	}
	
	public static Map<String, Field> allFieldsMap(Class<?> clazz){
		Map<String, Field> map = new HashMap<String, Field>();
		if(clazz != null && clazz != Object.class){
			Field[] fieldArray = clazz.getDeclaredFields();
			if(fieldArray!=null)
				for(Field field:fieldArray){
					if(field.getName().equalsIgnoreCase("serialVersionUID")) continue;
					map.put(field.getName(), field);
				}
			map.putAll(allFieldsMap(clazz.getSuperclass()));
		}
		return map;
	}
	
	public static Set<String> allFieldNames(Class<?> clazz){
		Set<String> fields = new LinkedHashSet<String>();	
		if(clazz != null && clazz != Object.class){
			Field[] fieldArray = clazz.getDeclaredFields();
			if(fieldArray!=null)
				for(Field field:fieldArray){
					if(field.getName().equalsIgnoreCase("serialVersionUID")) continue;
					fields.add(field.getName());
				}
			fields.addAll(allFieldNames(clazz.getSuperclass()));
		}
		return fields;
	}
	
	public static Object fieldValue(Object obj, String fieldName){
		Map<String, Field> allFieldsMap = allFieldsMap(obj.getClass());
		Field f = allFieldsMap.get(fieldName);
		if(f==null) return null;
		try {
			boolean originalAccess = f.isAccessible();
			f.setAccessible(true);
			Object value = f.get(obj);
			f.setAccessible(originalAccess);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Object fieldValue(Object obj, Field f){
		try {
			boolean originalAccess = f.isAccessible();
			f.setAccessible(true);
			Object value = f.get(obj);
			f.setAccessible(originalAccess);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {	
//		Set<Field> fields = allFields(AbstractSearch.class);
//		for(Field field : fields){
//			System.out.println(field.getName());
//		}
		
		class TestSearch extends AbstractSearch{
			String name;
			String city;
			String sortBy;
		}
		Collection<Field> fields = allFields(TestSearch.class);
		for(Field field : fields){
			System.out.println(field.getName());
		}
	}
}
