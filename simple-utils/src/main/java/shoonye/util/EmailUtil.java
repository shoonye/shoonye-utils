package shoonye.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 */
public class EmailUtil {
	private static String emailRegex = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+[.][A-Za-z]{2,4}";
	
	public static boolean isValidEmailAddress(String email){
		if (StringUtil.hasText(email)) return email.matches(emailRegex);
		else return false;
	}
		
	public static List<String> parseEmails(String emailIds){
		if(StringUtil.isBlank(emailIds)) return new ArrayList<String>(0);
		String[] emailArray = null;
		if(emailIds.contains(",")){
			emailArray = emailIds.split(",");
		}else if(emailIds.contains(";")){
        	emailArray = emailIds.split(";");
		}else{
			List<String> list = new ArrayList<String>(0);
			list.add(emailIds);
			return list;
		}
		return Arrays.asList(emailArray);
	}
}
