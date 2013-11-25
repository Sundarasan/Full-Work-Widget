package com.adaptavant.workwidget.utils;

public class StringValidator {
	
	public static String checkIfNull(String string) {
		
		if( string == null || string.equals("null") ) {
			string = "";
		}
		
		return string;
		
	}

}