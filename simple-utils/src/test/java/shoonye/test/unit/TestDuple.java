package shoonye.test.unit;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import shoonye.util.bean.Duple;




public class TestDuple {
	
	@Test
	public void testEqualsPositive(){
		Duple<Integer, String> d1 = new Duple<Integer, String>(1,"Test");
		Duple<Integer, String> d2 = new Duple<Integer, String>(1,"Testing");
		Duple<Integer, String> d3 = new Duple<Integer, String>(2,"Test");
		Assert.assertTrue(d1.equals(d2));
		Assert.assertFalse(d1.equals(d3));
	}
	
	@Test
	public void testSet(){
		Duple<Integer, String> d1 = new Duple<Integer, String>(1,"Test");
		Duple<Integer, String> d2 = new Duple<Integer, String>(1,"Testing");
		Duple<Integer, String> d3 = new Duple<Integer, String>(2,"Test");
		Set<Duple<Integer,String>> set  = new HashSet<Duple<Integer,String>>();
		set.add(d1);set.add(d2);set.add(d3);
		Assert.assertEquals(2, set.size());
	}
	
	@Test
	public void testEqualsNegative(){
		Duple<Integer, String> d1 = new Duple<Integer, String>(1,"Test");
		Duple<String, Integer> d2 = new Duple<String,Integer>("Test",1);
		Assert.assertFalse(d1.equals(d2));
	}
	
	@Test
	public void testInverse(){
		Duple<Integer, String> d1 = new Duple<Integer, String>(1,"Test");
		Duple<String, Integer> d2 = d1.inverse();
		Assert.assertTrue(d1.getKey().equals(d2.getValue()));
		Assert.assertTrue(d1.getValue().equals(d2.getKey()));
	}
}

