package shoonye.util.bean;

import java.util.Collection;
import java.util.TreeSet;

/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 * @param <E>
 */
public class SortedSearchResult<E> extends TreeSet<E> implements SearchResult<E>{
	private static final long serialVersionUID = -4257894237481963038L;
	private int totalCount;
	protected boolean hasMore;
	
	public SortedSearchResult(){}
	
	public SortedSearchResult(Collection<E> set, int totalCount){
		super(set);
		this.totalCount=totalCount; 
	}
	
	public SortedSearchResult(Collection<E> set, boolean hasMore){
		super(set);
		this.hasMore=hasMore; 
	}
	
	public SortedSearchResult(Collection<E> set){
		this(set,-1);
	}

	public int getTotalCount() {
		return totalCount;
	}
	
	public boolean hasMore(){
		return hasMore;
	}
	
	public void hasMore(boolean hasMore){
		 this.hasMore= hasMore;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}	
}

