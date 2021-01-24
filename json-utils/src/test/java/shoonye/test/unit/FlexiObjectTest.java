package shoonye.test.unit;

import org.junit.Assert;
import org.junit.Test;

import shoonye.json.util.FlexiObject;

public class FlexiObjectTest {
	@Test
	public void test$(){
		MyFlexiObject mfo = new MyFlexiObject();
		mfo.$("prop1", "value1");
		mfo.set("prop2", "value2");
		Assert.assertEquals("value1", mfo.getProp1());
		Assert.assertEquals("value1", mfo.$("prop1"));
		Assert.assertEquals("value2", mfo.$("prop2"));
	}
	
	class MyFlexiObject extends FlexiObject{
		private String prop1;

		public String getProp1() {
			return prop1;
		}

		public void setProp1(String prop1) {
			this.prop1 = prop1;
		}
		
	}
}
