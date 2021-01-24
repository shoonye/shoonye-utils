package shoonye.util;

import java.util.UUID;

/**
 * 
 * @author Anuradha Chowdhary
 * @author anuradha@shoonye.com
 *
 */
public class IDUtil {
	public static String uuid(){
		return UUID.randomUUID().toString();
	}
	public static String uuid2(){
		return UUID.randomUUID().getMostSignificantBits()+"";
	}
	public static void main(String args[]){
		System.out.println(uuid2());
	}
}
