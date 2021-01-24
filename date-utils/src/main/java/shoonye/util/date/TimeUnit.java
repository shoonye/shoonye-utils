package shoonye.util.date;

/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 */
public enum TimeUnit {
	SECOND, MINUTE, HOUR, DAY, WEEK, MONTH, YEAR;
	

	public String singular(){
		return name().toLowerCase();
	}
	
	public String plural(){
		return name().toLowerCase()+"s";
	}
}
