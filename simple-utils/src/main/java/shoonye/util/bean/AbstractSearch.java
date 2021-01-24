package shoonye.util.bean;

/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 */
public abstract class AbstractSearch{
	protected int max=20;
	protected int offset;
	protected boolean asc = true;
	protected String sortBy;
	private boolean countRequired=false;
	private int totalCount;
	
	public AbstractSearch(){
		offset=0;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int firstResult) {
		this.offset = firstResult;
	}
	public int getLastResult() {
		return offset+max;
	}
	
	public boolean isAsc() {
		return asc;
	}
	public void setAsc(boolean asc) {
		this.asc = asc;
	}
	public String getSortBy() {
		if (sortBy!=null)
			return sortBy.trim();
		return null;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public boolean isCountRequired() {
		return countRequired;
	}
	public void setCountRequired(boolean countRequired) {
		this.countRequired = countRequired;
	}
	
	public void setPageNumber(int pageNumber){
		offset=(pageNumber-1)*max;
	}
	
	/**
	 * @return page number starting from 1
	 */
	public int getPageNumber(){
		return (int)Math.floor(offset/max) + 1;
	}
	
	public void nextPage(){
		offset = offset + max;
	}
	
	public void previousPage(){
		offset = offset - max;
	}
	
	public void firstPage(){
		offset = 0;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int maxResult) {
		this.max = maxResult;
	}
	
	public boolean hasMoreIfCount(long count){
	    boolean hasMore = false;
        if(count > (offset + max)) {
            hasMore = true;
        }
        return hasMore;
	}
}
