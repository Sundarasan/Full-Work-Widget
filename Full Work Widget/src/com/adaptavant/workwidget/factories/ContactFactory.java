package com.adaptavant.workwidget.factories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.codehaus.jackson.type.TypeReference;

import com.adaptavant.workwidget.constants.Urls;
import com.adaptavant.workwidget.constants.FinalVariables;
import com.adaptavant.workwidget.dto.Contact;
import com.adaptavant.workwidget.utils.UrlFetchUtil;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class ContactFactory {
	
	private static Logger logger = Logger.getLogger( ContactFactory.class.getPackage().getName());
	
	public static HashMap<String, Object> getContact( String apiKey, String contactId) {
		
		boolean isSuccess = false;
		HashMap<String, Object> returnHashMap = new HashMap<String, Object>();
		
		try {
			
			String responseString = UrlFetchUtil.httpRequest( Urls.getContactUrl( contactId ), null, "GET", null, null, 2);
			
			JSONObject responseJson = new JSONObject( responseString );
			
			if( responseJson.has("success") && (boolean) responseJson.get("success") ) {
				
				Contact contact = FinalVariables.getObjectMapper().readValue( String.valueOf( responseJson.get("contact") ), new TypeReference<Contact>() {});
				
				returnHashMap.put("contact", contact);
				
				isSuccess = true;
			}
			
		} catch (Exception e) {
			
			logger.log(Level.SEVERE, "Error Path : " + ContactFactory.class.getPackage().getName() + "; Method : getContact(); Detail : Error while getting a contact.");
			logger.log(Level.SEVERE, e.getMessage(), e);
			
		} finally {
			returnHashMap.put("success", isSuccess);
		}
		
		return returnHashMap;
		
	}
	
	public static HashMap<String, Object> getRequiredContacts( String accountId, String requestBody ) {
		
		boolean isSuccess = false;
		HashMap<String, Object> returnHashMap = new HashMap<String, Object>();
		
		try {

			String responseString = UrlFetchUtil.httpRequest( Urls.getGetReqContactsUr( accountId ) , requestBody, "POST", "application/json", null, 2);

			JSONObject responseJson = new JSONObject( responseString );
			
			if( responseJson.has("success") && (boolean) responseJson.get("success") ) {
				
				ArrayList<Contact> arrayListOfContacts = FinalVariables.getObjectMapper().readValue( String.valueOf( responseJson.get("contact") ), new TypeReference< ArrayList<Contact> >() {});
				
				returnHashMap.put("contact", arrayListOfContacts);
				
				isSuccess = true;
			}
			
		} catch (Exception e) {
			
			logger.log(Level.SEVERE, "Error Path : " + ContactFactory.class.getPackage().getName() + "; Method : getRequiredContacts(); Detail : Error while getting required contacts.");
			logger.log(Level.SEVERE, e.getMessage(), e);
			
		} finally {
			returnHashMap.put("success", isSuccess);
		}
		
		return returnHashMap;
		
	}
	
	public static HashMap<String, Object> getAllContacts( String accountId ) {
		
		boolean isSuccess = false;
		HashMap<String, Object> returnHashMap = new HashMap<String, Object>();
		
		try {
			
			String responseString = UrlFetchUtil.httpRequest( Urls.getUserAndSkillSetsUrl( accountId ), null, "GET", null, null, 2);
			
			JSONObject responseJson = new JSONObject( responseString );
			
			if( responseJson.has("success") && (boolean) responseJson.get("success") ) {
				
				ArrayList<Contact> arrayListOfContact = FinalVariables.getObjectMapper().readValue( String.valueOf( responseJson.get("person") ), new TypeReference<ArrayList<Contact>>() {});
				
				returnHashMap.put("persons", arrayListOfContact);
				
				isSuccess = true;
			}
			
		} catch (Exception e) {
			
			logger.log(Level.SEVERE, "Error Path : " + ContactFactory.class.getPackage().getName() + "; Method : getContact(); Detail : Error while getting a contact.");
			logger.log(Level.SEVERE, e.getMessage(), e);
			
		} finally {
			returnHashMap.put("success", isSuccess);
		}
		
		return returnHashMap;
		
	}

}
