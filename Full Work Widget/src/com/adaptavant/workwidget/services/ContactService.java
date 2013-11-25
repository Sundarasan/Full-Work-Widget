package com.adaptavant.workwidget.services;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.adaptavant.workwidget.factories.ContactFactory;
import com.adaptavant.workwidget.dto.Contact;

public class ContactService {
	
	private static Logger logger = Logger.getLogger( ContactService.class.getPackage().getName());
	
	public static HashMap<String, Object> getContact( String apiKey, String contactId) {
		
		boolean isSuccess = false;
		HashMap<String, Object> returnHashMap = new HashMap<String, Object>();
		
		try {
			
			HashMap<String, Object> hashMapOfContact = ContactFactory.getContact( apiKey, contactId);
			
			if( hashMapOfContact.containsKey("success") && (boolean) hashMapOfContact.get("success") ) {
				
				Contact contact = (Contact) hashMapOfContact.get("contact");
				
				returnHashMap.put("contact", contact);
				
				isSuccess = true;
				
			}
			
		} catch (Exception e) {
			
			logger.log(Level.SEVERE, "Error Path : " + TaskService.class.getPackage().getName() + "; Method : getTasks(); Detail : Error while getting the tasks.");
			logger.log(Level.SEVERE, e.getMessage(), e);
			
		} finally {
			returnHashMap.put("success", isSuccess);
		}
		
		return returnHashMap;
	}
	
	public static HashMap<String, Object> getRequiredContacts( String accountId, String requestBody ) {
		
		return ContactFactory.getRequiredContacts( accountId, requestBody );
		
	}
	
	public static HashMap<String, Object> getAllContacts( String accountId ) {
	
		return ContactFactory.getAllContacts( accountId );
		
	}

}
