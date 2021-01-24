package shoonye.util;

/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 */
public class StringUtil {
	public static boolean isBlank(String str){
		return str == null || str.trim().isEmpty();
	}
	
	public static boolean hasText(String str){
		return str != null && !str.trim().isEmpty();
	}
}
