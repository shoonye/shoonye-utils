package shoonye.test.unit;

import shoonye.json.ObjectMapper;
import shoonye.json.util.FlexiObject;
import shoonye.util.bean.Duple;

public class Main {
	public static void main(String[] args) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		MyFlexiObject fobj = new MyFlexiObject();
		fobj.setId("_id1");
		fobj.set("name", "Anuradha");
		fobj.set("city", "Indore");
		fobj.setTemp("tmp");
		fobj.setCreatedDate(null);
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(fobj);
		System.out.println(json);
		FlexiObject fo = mapper.readValue(json, MyFlexiObject.class);
		json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(fo);
	    System.out.println(json);
	}
	
	public static class MyFlexiObject extends FlexiObject {
		private String temp="temp";
		private Duple<String, Integer> duple = new Duple<String, Integer>("test", 1);
		
		public String getTemp() {
			return temp;
		}
		public void setTemp(String temp) {
			this.temp = temp;
		}
		public Duple<String, Integer> getDuple() {
			return duple;
		}
		public void setDuple(Duple<String, Integer> duple) {
			this.duple = duple;
		}
	}

}
