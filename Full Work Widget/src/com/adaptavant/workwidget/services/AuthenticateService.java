package com.adaptavant.workwidget.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.type.TypeReference;

import com.adaptavant.workwidget.constants.FinalVariables;
import com.adaptavant.workwidget.dto.Account;
import com.adaptavant.workwidget.dto.Contact;
import com.adaptavant.workwidget.factories.AccountFactory;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class AuthenticateService {
	
	private static Logger logger = Logger.getLogger( AuthenticateService.class.getPackage().getName());
	
	public static HashMap<String, Object> validateUser( String state, String code, HttpServletRequest req, HttpServletResponse resp) {
		
		boolean isSuccess = false;
		HashMap<String, Object> returnHashMap = new HashMap<String, Object>();
		
		try {
			String googleJsonResponseString = FinalVariables.getGoogleAuthHelper().getUserInfoJson( code );
			
			JSONObject jsonOfGoogleAccountInfo = new JSONObject(googleJsonResponseString);
			String eMail = String.valueOf( jsonOfGoogleAccountInfo.get("email") );
			
			HashMap<String, Object> hashMapOfAccount = AccountFactory.authenticateAccount(eMail);
			
			if( hashMapOfAccount.containsKey("success") && (boolean) hashMapOfAccount.get("success") ) {
				
				String jsonString = String.valueOf( hashMapOfAccount.get("account") );
				
				JSONObject jsonObject = new JSONObject( jsonString );
				
				Contact contact = FinalVariables.getObjectMapper().readValue( jsonObject.get("contact").toString() ,  new TypeReference<Contact>() {});
				Account account = FinalVariables.getObjectMapper().readValue( jsonObject.get("account").toString() ,  new TypeReference<Account>() {});
				
				returnHashMap.put("account", account);
				returnHashMap.put("contact", contact);
				
				isSuccess = true;

			}
			
		} catch (Exception e) {
			
			logger.log(Level.SEVERE, "Error Path : " + AuthenticateService.class.getPackage().getName() + "; Method : validateUser(); Detail : Error while authenticating account.");
			logger.log(Level.SEVERE, e.getMessage(), e);
			
		} finally {
			returnHashMap.put("success", isSuccess);
		}
		
		return returnHashMap;
	}
	
}
