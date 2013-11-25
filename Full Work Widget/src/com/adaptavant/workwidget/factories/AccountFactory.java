package com.adaptavant.workwidget.factories;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.codehaus.jackson.type.TypeReference;

import com.adaptavant.workwidget.constants.Urls;
import com.adaptavant.workwidget.constants.FinalVariables;
import com.adaptavant.workwidget.dto.*;
import com.adaptavant.workwidget.utils.UrlFetchUtil;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class AccountFactory {
	
	private static Logger logger = Logger.getLogger(AccountFactory.class.getPackage().getName());
	
	public static HashMap<String, Object> authenticateAccount(String login) {
		
		boolean isSuccess = false;
		HashMap< String, Object> returnHashMap = new HashMap<String, Object>();
		
		try {
		
			HashMap<String, String> requestHashMap = new HashMap<String, String>();
		
			try {
				requestHashMap.put("login", login);
			} catch(Exception e) {
				logger.log(Level.SEVERE, "Error Path : " + AccountFactory.class.getPackage().getName() + "; Method : getUser(); Detail : Error while putting data into HashMap.");
				logger.log(Level.SEVERE, e.getMessage(), e);
			}
			String requestQueryString = new JSONObject(requestHashMap.toString()).toString();
		
			String responseString = UrlFetchUtil.httpRequest(Urls.getValidateAccoutUrl(), requestQueryString, "POST", "application/json", null, 5);
			//String responseString = "{'skillSet':null,'status':true,'account':{'brandID':'801e4cdf-9fb6-4038-8de2-61719023c125','name':null,'deleted':false,'value':0.0,'rating':null,'status':'open','ownerID':null,'source':'DistributedSource','createdDate':1380844800000,'lastUpdatedDate':1380885361119,'linkedContacts':['940eb647-bcf6-43e7-973b-e5c181d4cc4c','32510b31-3f59-4ea1-aa1a-20469a5546a4'],'linkedTasks':[],'linkedHistory':[],'billed':false,'linkedTags':[],'linkedDocuments':[],'linkedProducts':['801e4cdf-9fb6-4038-8de2-61719023c125'],'linkedCustomFields':[],'linkedSubscribers':[],'parentAccountID':'SEN42','documentsSpace':0.22834205627441406,'notes':'','id':'b1119fc8-e4c4-4f33-904f-a17788aa02a2'},'contactSkillSet':[{'accountID':'b1119fc8-e4c4-4f33-904f-a17788aa02a2','contactID':'940eb647-bcf6-43e7-973b-e5c181d4cc4c','brandID':null,'deleted':false,'createdDate':1380885361418,'skillLevel':'','skillSetID':'532683df-9b8d-4063-a50a-9e1de41f3abb','url':'','id':'8c4a1587-b4f5-43e9-a26d-d8975c76e197'}],'isLoginExist':true,'contact':{'type':'contact','category':'person','fullName':null,'firstName':'Sundarasan','middleName':null,'lastName':'Natarajan','deleted':false,'createdDate':1380885359729,'lastUpdatedDate':1380978550592,'accountNumber':null,'source':null,'title':null,'login':'sundarasan.natarajan@a-cti.com','password':'5f4dcc3b5aa765d61d8327deb882cf99','comments':'','ownerID':null,'brandID':'801e4cdf-9fb6-4038-8de2-61719023c125','subBrandID':null,'photoID':'','timeZone':null,'department':null,'linkedContacts':[],'linkedDeals':[],'linkedHistory':[],'linkedTasks':[],'linkedAccounts':['b1119fc8-e4c4-4f33-904f-a17788aa02a2'],'linkedTags':[],'linkedDocuments':[],'linkedCustomFields':[],'linkedContactMethods':[{'key':{'kind':'ContactMethod','appId':'s~distributedsource-cms','id':5838406743490560,'name':null,'parent':{'kind':'Contact','appId':'s~distributedsource-cms','id':0,'name':'940eb647-bcf6-43e7-973b-e5c181d4cc4c','parent':null,'namespace':'','complete':true},'namespace':'','complete':true},'type':'email','title':'email','value':'sdf@sdf.com','deleted':true,'createdDate':1380978550592,'primary':false,'typeID':'e8f41ae1-4ff9-40c4-9a35-3c962952e08a','locationID':null,'accountID':'SEN42','id':'29051c5e-e51d-4195-b1ad-107dcf17081a'},{'key':{'kind':'ContactMethod','appId':'s~distributedsource-cms','id':5629499534213120,'name':null,'parent':{'kind':'Contact','appId':'s~distributedsource-cms','id':0,'name':'940eb647-bcf6-43e7-973b-e5c181d4cc4c','parent':null,'namespace':'','complete':true},'namespace':'','complete':true},'type':'email','title':null,'value':'sundarasan.natarajan@a-cti.com','deleted':false,'createdDate':1380885359730,'primary':true,'typeID':'e8f41ae1-4ff9-40c4-9a35-3c962952e08a','locationID':null,'accountID':'SEN42','id':'1aded10d-1abf-41ef-bae9-6c40e1961cf5'}],'linkedLocations':[],'accountID':'SEN42','id':'940eb647-bcf6-43e7-973b-e5c181d4cc4c','ssn':null,'ipaddress':null}}";
			
			JSONObject jsonResponse = new JSONObject(responseString);
			
			if( (boolean) jsonResponse.get("isLoginExist") ) {
				
				returnHashMap.put("account", String.valueOf( jsonResponse ) );
				
				isSuccess = true;
				
			}
			
		
		} catch(Exception e) {
			logger.log(Level.SEVERE, "Error Path : " + AccountFactory.class.getPackage().getName() + "; Method : getUser(); Detail : Error while getting person details.");
			logger.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			returnHashMap.put("success", isSuccess);
		}
		return returnHashMap;
	}
	
	public static HashMap<String, Object> getAccount(String accountId) {
		
		boolean isSuccess = false;
		HashMap<String, Object> returnHashMap = new HashMap<String, Object>();
		
		try {
		
			System.out.println( "URL : " + Urls.getAccountUrl( accountId ) );
			String responseString = UrlFetchUtil.httpRequest( Urls.getAccountUrl( accountId ), null, "GET", null /*Content Type*/, null, 5);
			//String responseString = "{'skillSet':[{'skillSetId':'c3649b38-4799-4bf3-8c08-b30527a98b88','skillSetTypeId':'70158413-3ae0-4896-80b7-50d411ad0cd2','title':'AnswerConnect','description':'Permissions to take chats those come from AnswerConnect.com','dateAdded':1231146333000,'v2skilled':null}],'person':[{'type':'contact','category':'person','fullName':null,'firstName':'Sundarasan','middleName':null,'lastName':'Natarajan','deleted':false,'createdDate':1380885359729,'lastUpdatedDate':1380978550592,'accountNumber':null,'source':null,'title':null,'login':'sundarasan.natarajan@a-cti.com','password':'5f4dcc3b5aa765d61d8327deb882cf99','comments':'','ownerID':null,'brandID':'801e4cdf-9fb6-4038-8de2-61719023c125','subBrandID':null,'photoID':'','timeZone':null,'department':null,'linkedContacts':[],'linkedDeals':[],'linkedHistory':[],'linkedTasks':[],'linkedAccounts':['b1119fc8-e4c4-4f33-904f-a17788aa02a2'],'linkedTags':[],'linkedDocuments':[],'linkedCustomFields':[],'linkedContactMethods':[{'key':{'kind':'ContactMethod','appId':'s~distributedsource-cms','id':5838406743490560,'name':null,'parent':{'kind':'Contact','appId':'s~distributedsource-cms','id':0,'name':'940eb647-bcf6-43e7-973b-e5c181d4cc4c','parent':null,'namespace':'','complete':true},'namespace':'','complete':true},'type':'email','title':'email','value':'sdf@sdf.com','deleted':true,'createdDate':1380978550592,'primary':false,'typeID':'e8f41ae1-4ff9-40c4-9a35-3c962952e08a','locationID':null,'accountID':'SEN42','id':'29051c5e-e51d-4195-b1ad-107dcf17081a'},{'key':{'kind':'ContactMethod','appId':'s~distributedsource-cms','id':5629499534213120,'name':null,'parent':{'kind':'Contact','appId':'s~distributedsource-cms','id':0,'name':'940eb647-bcf6-43e7-973b-e5c181d4cc4c','parent':null,'namespace':'','complete':true},'namespace':'','complete':true},'type':'email','title':null,'value':'sundarasan.natarajan@a-cti.com','deleted':false,'createdDate':1380885359730,'primary':true,'typeID':'e8f41ae1-4ff9-40c4-9a35-3c962952e08a','locationID':null,'accountID':'SEN42','id':'1aded10d-1abf-41ef-bae9-6c40e1961cf5'}],'linkedLocations':[],'accountID':'SEN42','id':'940eb647-bcf6-43e7-973b-e5c181d4cc4c','ssn':null,'ipaddress':null},{'type':'contact','category':'person','fullName':null,'firstName':'Me','middleName':null,'lastName':'User','deleted':false,'createdDate':1383224196975,'lastUpdatedDate':1383224266100,'accountNumber':null,'source':null,'title':null,'login':'sundarasanScript editor" category="
			
			JSONObject jsonResponse = new JSONObject( responseString );
			
			if( (boolean) jsonResponse.get("success") ) {
				
				returnHashMap.put("account", String.valueOf( jsonResponse ) );
				
				isSuccess = true;
				
			}
			
		
		} catch(Exception e) {
			logger.log(Level.SEVERE, "Error Path : " + AccountFactory.class.getPackage().getName() + "; Method : getAccount(); Detail : Error while getting account details.");
			logger.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			returnHashMap.put("success", isSuccess);
		}
		return returnHashMap;
	}
	
}