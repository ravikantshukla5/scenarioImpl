package com.ramselabs.scenario.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class HashPasswordUtil {
	public static String hashPassword(String password)
			throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(password.getBytes());

		byte byteData[] = md.digest();

		// convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
					.substring(1));
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter password to hash");
		String password = input.nextLine();
		
		try {
			System.out.println("Hashed password: " + hashPassword(password));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		input.close();
	}

}
