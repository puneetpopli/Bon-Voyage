package com.hangout.amigos.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.joda.time.DateTime;


/**
 * 
 * @author puneetpopli
 *
 */
public class HangoutAmigosUtil {

	//Generate unique ID for user
	public static Integer generateUserId(Integer val) {

		DateTime date = new DateTime();
		Integer year = date.getYear();
		Integer month = date.getMonthOfYear();
		Integer day = date.getDayOfMonth();
		Integer hour = date.getHourOfDay();
		Integer milli = date.getMillisOfDay();
		Integer milliSec = date.getMillisOfSecond();

		Integer userId = val + year+milli+month+day+milliSec+hour;

		return userId;

	}

	public static String passwordEncryption(String password) {
		
		String encryptedPassword = null;
		
		try {
			MessageDigest digester = MessageDigest.getInstance("MD5");

			digester.update(password.getBytes());

			byte[] hash = digester.digest();

			StringBuffer sb = new StringBuffer();

			for(int i=0; i<hash.length; i++) {
				if ((0xff & hash[i]) < 0x10) {
					sb.append("0" + Integer.toHexString((0xFF & hash[i])));
				}
				else {
					sb.append(Integer.toHexString(0xFF & hash[i]));
				}
			}
			
			encryptedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		return encryptedPassword;
	}
	
	public static int getRandomInteger(){
		Random random = new Random();
		return random.nextInt(Integer.MAX_VALUE)  + 1 ;
	}

}
