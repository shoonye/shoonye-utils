package shoonye.util.date;
/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 */
public class TimeFilter {
	private int count;
	private TimeUnit unit;
	
	public TimeFilter(int count, TimeUnit unit) {
        this.count = count;
        this.unit = unit;
    }
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public TimeUnit getUnit() {
		return unit;
	}
	public void setUnit(TimeUnit unit) {
		this.unit = unit;
	}
	
	@Override
	public String toString() {
		return count + " " + (count >1 ? unit.plural(): unit.singular());
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeFilter other = (TimeFilter) obj;
		if (count != other.count)
			return false;
		if (unit != other.unit)
			return false;
		return true;
	}
	
}
