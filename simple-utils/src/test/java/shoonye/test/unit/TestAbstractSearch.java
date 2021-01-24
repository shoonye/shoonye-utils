package shoonye.test.unit;


import org.junit.Assert;
import org.junit.Test;

import shoonye.util.bean.AbstractSearch;

public class TestAbstractSearch {
	
	@Test
	public void testPageNumber(){
		AbstractSearch search = new TestSearch();
		
		search.setOffset(10);
		search.setMax(10);
		Assert.assertEquals(2, search.getPageNumber());
	}
	
	@Test
	public void testOffset(){
		AbstractSearch search = new TestSearch();
		search.setMax(50);
		search.setPageNumber(3);
		Assert.assertEquals(100, search.getOffset());
	}
	
	static class TestSearch extends AbstractSearch{
		
	}
}
