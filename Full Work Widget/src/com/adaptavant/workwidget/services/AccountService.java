package com.adaptavant.workwidget.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import com.adaptavant.workwidget.factories.AccountFactory;
import com.adaptavant.workwidget.dto.Contact;

public class AccountService {

	private static Logger logger = Logger.getLogger( AccountService.class.getPackage().getName());
	
	public static HashMap<String, Object> getPersons(String accountId) {
		
		boolean isSuccess = false;
		HashMap<String, Object> returnHashMap = new HashMap<String, Object>();
		
		try {
		
			HashMap<String, Object> hashMapOfAccountDetails = AccountFactory.getAccount( accountId );
		
			if( hashMapOfAccountDetails.containsKey("success") && (boolean) hashMapOfAccountDetails.get("success") ) {
			
				ArrayList<Contact> arrayListOfPerson = (ArrayList<Contact>) hashMapOfAccountDetails.get("person");
			
				returnHashMap.put("persons", arrayListOfPerson);
				
				isSuccess = true;
				
			}
		} catch(Exception e) {
			
		} finally {
			
			returnHashMap.put("success", isSuccess);
			
		}
		
		return returnHashMap;
		
	}
	
}
