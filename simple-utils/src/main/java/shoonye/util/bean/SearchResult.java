package shoonye.util.bean;

import java.util.Set;

/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 * @param <E>
 */
public interface SearchResult<E> extends Set<E>{
	public abstract boolean hasMore();
	public abstract int getTotalCount();

}
