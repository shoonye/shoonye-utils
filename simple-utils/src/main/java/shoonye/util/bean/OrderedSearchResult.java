package shoonye.util.bean;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 * @param <E>
 */
public class OrderedSearchResult<E> extends LinkedHashSet<E> implements SearchResult<E>{
	private static final long serialVersionUID = -4257894237481963038L;
	private int totalCount;
	protected boolean more;
	
	public OrderedSearchResult(){}
	
	public OrderedSearchResult(Collection<E> set, int totalCount){
		super(set);
		this.totalCount=totalCount; 
	}
	
	public OrderedSearchResult(Collection<E> set, boolean hasMore){
		super(set);
		this.more=hasMore; 
	}
	
	public OrderedSearchResult(Collection<E> set){
		this(set,-1);
	}

	@Override
	public boolean hasMore(){
		return more;
	}
	
	public void setMore(boolean hasMore) {
		this.more = hasMore;
	}

	@Override
	public int getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}	
}

