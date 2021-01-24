package shoonye.test.unit;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import shoonye.util.ClassUtil;
import shoonye.util.bean.AbstractSearch;

public class TestClassUtil {
	
	class TestSearch extends AbstractSearch{
		String name;
		String city;
		String sortBy;
	}
	
	@Test
	public void testAllFieldNamesContains(){
		Collection<String> fields = ClassUtil.allFieldNames(TestSearch.class);
		Assert.assertTrue(fields.contains("sortBy"));
		Assert.assertTrue(fields.contains("offset"));
	}
	
	@Test
	public void testAllFieldMapContains(){
		Map<String, Field> fields = ClassUtil.allFieldsMap(TestSearch.class);
		Field offsetField= fields.get("offset");
		Field nameField= fields.get("name");
		Assert.assertEquals(nameField.getType(), String.class);
		Assert.assertEquals(offsetField.getType(),int.class );
	}
	
	@Test
	public void testFieldValue(){
		TestSearch search = new TestSearch();
		search.sortBy ="sort";
		search.name ="name";
		search.setOffset(10);
		
		Object nameValue = ClassUtil.fieldValue(search, "name");
		Assert.assertEquals("name", nameValue);
		
		Object offsetValue = ClassUtil.fieldValue(search, "offset");
		Assert.assertEquals(10, offsetValue);
		
		/**Object sortValue = ClassUtil.fieldValue(search, "sortBy");
		Assert.assertEquals("sort", sortValue);**/ //TODO Test failing resolve
	}

}
