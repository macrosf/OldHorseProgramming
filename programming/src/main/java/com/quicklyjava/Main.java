package com.quicklyjava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			InputStreamReader in = new InputStreamReader(System.in);
			BufferedReader reader = new BufferedReader(in);
			System.out.println("Enter Class Name: ");
			String whatClass = reader.readLine();
			Class<?> exampleClass = Class.forName(whatClass);

			@SuppressWarnings("unused")
			Object ob = exampleClass.newInstance();
	 	} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
