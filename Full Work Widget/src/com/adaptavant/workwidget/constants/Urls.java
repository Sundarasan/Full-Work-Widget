package com.adaptavant.workwidget.constants;

import com.google.api.server.spi.config.ApiKey;

public class Urls {
	
	private static final String cmsLiveUrl = "https://distributedsource-cms.appspot.com";
	private static final String cmsStagingUrl = "https://distributedsource-client.appspot.com";
	private static final String cmsApiObjectAccessUrl = "/services/data/v2.0/objects";
	private static final String cmsAccountUrl = "/Account";
	private static final String cmsContactUrl = "/Contact";
	private static final String cmsValidationUrl = "/validateLogin";
	private static final String charset = "";
	private static final String cmsGetUserAndSkillSetsUrl = "/getUsersAndSkillSets";
	private static final String cmsGetSpecificAccountUrl = "/getAccount";
	private static final String cmsApiSyntax = "apikey=";
	private static final String cmsApiKey = "SEN42";
	private static final String createTaskUrl = "/createTask";
	private static final String userIdSyntax = "userid=";
	private static final String ownerIdSyntax = "ownerid=";
	private static final String readTaskByAssigneeIdUrl = "/readTask";
	private static final String readTaskByOwnerIdUrl = "/readOwnerTasks";
	private static final String updateTaskUrl = "/updateATask";
	private static final String deleteATaskUrl = "/deleteATask";
	private static final String getReqContactsUrl = "/getReqContacts";
	
	public static String getValidateAccoutUrl() {
		return cmsLiveUrl + cmsApiObjectAccessUrl + cmsAccountUrl + cmsValidationUrl + "?" + cmsApiSyntax + cmsApiKey;
	}
	
	public static String getCharset() {
		return charset;
	}
	
	public static String getAccountUrl( String accountId ) {
		return cmsLiveUrl + cmsApiObjectAccessUrl + cmsAccountUrl + "/" + accountId + cmsGetSpecificAccountUrl + "?" + cmsApiSyntax + cmsApiKey;
	}
	
	public static String getUserAndSkillSetsUrl( String accountId ) {
		return cmsLiveUrl + cmsApiObjectAccessUrl + cmsAccountUrl + "/" + accountId + cmsGetUserAndSkillSetsUrl + "?" + cmsApiSyntax + cmsApiKey;
	}
	
	public static String getContactUrl( String contactId ) {
		return cmsLiveUrl + cmsApiObjectAccessUrl + cmsContactUrl + "/" + contactId + "?" + cmsApiSyntax + cmsApiKey;
	}
	
	public static String getApiSyntaxUrl() {
		return cmsApiSyntax;
	}
	
	public static String getApiKeyUrl() {
		return cmsApiSyntax + cmsApiKey;
	}
	
	public static String getCmsapikey() {
		return cmsApiKey;
	}

	public static String getCreateTaskUrl( String accountId, String userId ) {
		return cmsStagingUrl + createTaskUrl + "?" + cmsApiSyntax + accountId + "&" + userIdSyntax + userId ;
	}
	
	public static String getUserIdSyntax() {
		return userIdSyntax;
	}
	
	public static String getReadTasksByAssigneeIdUrl( String accountId, String assigneeId ) {
		return cmsStagingUrl + readTaskByAssigneeIdUrl + "/" + assigneeId + "?" + cmsApiSyntax + accountId + "&" + userIdSyntax + assigneeId;
	}
	
	public static String getReadTasksByOwnerIdUrl( String accountId, String userId, String ownerId ) {
		return cmsStagingUrl + readTaskByOwnerIdUrl + "?" + cmsApiSyntax + accountId + "&" + userIdSyntax + userId +  "&" + ownerIdSyntax + ownerId ;
	}
	
	public static String getUpdateTaskUrl( String accountId, String userId, String taskId ) {
		return cmsStagingUrl + updateTaskUrl + "/" + taskId + "?" + cmsApiSyntax + accountId + "&" + userIdSyntax + userId;
	}

	public static String getGetReqContactsUr( String accountId ) {
		return cmsLiveUrl + cmsApiObjectAccessUrl + cmsContactUrl + getReqContactsUrl + "?" + cmsApiSyntax + accountId;
	}
	
}
