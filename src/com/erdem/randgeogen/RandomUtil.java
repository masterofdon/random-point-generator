package com.erdem.randgeogen;

import java.util.Random;

public class RandomUtil {

	public static String randomAlphanumeric(int lenght){
		String SALTCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
		Random rand = new Random();
		while (salt.length() < 18) { // length of the random string.
            int index = (int) (rand.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
	}
	
}
