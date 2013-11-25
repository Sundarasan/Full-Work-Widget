package com.adaptavant.workwidget.constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import com.adaptavant.workwidget.helpers.GoogleAuthHelper;


public class FinalVariables {
	
	private static final String appMode = "dev";
	private static final String devGoogleOathCallBackUri = "http://localhost:8888/oauth2callback";
	private static final String stagingGoogleOathCallBackUri = "http://workwidget.appspot.com/oauth2callback";
	private static final String liveGoogleOathCallBackUri = "";
	
	private static JsonFactory factory 		 = new JsonFactory();
	private static final ObjectMapper mapper = new ObjectMapper(factory);
	
	private static final GoogleAuthHelper googleAuthHelper = new GoogleAuthHelper();
	
	static{
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
	}
	
	public static ObjectMapper getObjectMapper() {
		return mapper;
	}
	
	public static GoogleAuthHelper getGoogleAuthHelper() {
		return googleAuthHelper;
	}
	
	public static String getGoogleOauthCallBackUri() {
		
		switch( appMode ) {
		case "staging":
			return stagingGoogleOathCallBackUri;
		case "live":
			return liveGoogleOathCallBackUri;
		case "dev":
			return devGoogleOathCallBackUri;
		default:
			return liveGoogleOathCallBackUri;
		}
		
	}
	
}
