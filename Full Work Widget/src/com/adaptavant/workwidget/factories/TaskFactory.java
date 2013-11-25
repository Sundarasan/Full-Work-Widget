package com.adaptavant.workwidget.factories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.type.TypeReference;

import com.adaptavant.workwidget.constants.FinalVariables;
import com.adaptavant.workwidget.constants.Urls;
import com.adaptavant.workwidget.dto.Contact;
import com.adaptavant.workwidget.dto.Task;
import com.adaptavant.workwidget.dto.TaskTemp;
import com.adaptavant.workwidget.utils.TaskTempAndTaskConverter;
import com.adaptavant.workwidget.utils.UrlFetchUtil;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class TaskFactory {
	
private static Logger logger = Logger.getLogger( ContactFactory.class.getPackage().getName());
	
	public static HashMap<String, Object> createTask( String apiKey, String userId, Task taskToCreate ) {
		
		boolean isSuccess = false;
		HashMap<String, Object> returnHashMap = new HashMap<String, Object>();
		
		try {
			
			TaskTemp taskTempToCreate = TaskTempAndTaskConverter.taskToTaskTemp( taskToCreate );
			
			JSONObject taskTempToCreateJson = new JSONObject( taskTempToCreate );
			
			String responseString = UrlFetchUtil.httpRequest( Urls.getCreateTaskUrl( apiKey, userId ), String.valueOf( taskTempToCreateJson ), "POST",  "application/json", null, 2);
			
			JSONObject responseJson = new JSONObject( responseString );
			
			if( responseJson.has("status") && (boolean) responseJson.get("status") ) {
				
				TaskTemp createdTaskTemp = FinalVariables.getObjectMapper().readValue( String.valueOf( responseJson.get("task") ), new TypeReference<TaskTemp>() {});
				
				Task createdTask = TaskTempAndTaskConverter.taskTempToTask( createdTaskTemp );
				
				returnHashMap.put("task", createdTask);
				
				isSuccess = true;
				
			}
			
		} catch (Exception e) {
			
			logger.log(Level.SEVERE, "Error Path : " + TaskFactory.class.getPackage().getName() + "; Method : createTask(); Detail : Error while creating a task.");
			logger.log(Level.SEVERE, e.getMessage(), e);
			
		} finally {
			returnHashMap.put("success", isSuccess);
		}
		
		return returnHashMap;
		
	}
	
	public static HashMap<String, Object> updateTask( String apiKey, String userId, Task taskToUpdate ) {
		
		boolean isSuccess = false;
		HashMap<String, Object> returnHashMap = new HashMap<String, Object>();
		
		try {
			
			TaskTemp taskTempToUpdate = TaskTempAndTaskConverter.taskToTaskTemp( taskToUpdate );
			
			JSONObject taskToUpdateJson = new JSONObject( taskTempToUpdate );

			String responseString = UrlFetchUtil.httpRequest( Urls.getUpdateTaskUrl( apiKey, userId, String.valueOf( taskToUpdateJson.get("ID") ) ), String.valueOf( taskToUpdateJson ), "PUT",  "application/json", null, 2);
			
			JSONObject responseJson = new JSONObject( responseString );
			
			if( responseJson.has("status") && (boolean) responseJson.get("status") ) {
				
				TaskTemp createdTaskTemp = FinalVariables.getObjectMapper().readValue( String.valueOf( responseJson.get("task") ), new TypeReference<TaskTemp>() {});
				
				Task createdTask = TaskTempAndTaskConverter.taskTempToTask( createdTaskTemp );
				
				returnHashMap.put("task", createdTask);
				
				isSuccess = true;
				
			}
			
		} catch (Exception e) {
			
			logger.log(Level.SEVERE, "Error Path : " + TaskFactory.class.getPackage().getName() + "; Method : createTask(); Detail : Error while creating a task.");
			logger.log(Level.SEVERE, e.getMessage(), e);
			
		} finally {
			returnHashMap.put("success", isSuccess);
		}
		
		return returnHashMap;

	}
	
	public static HashMap<String, Object> deleteTask() {
		
		
		
		return new HashMap<String, Object>();
	}
	
	public static HashMap<String, Object> getTasksByAssigneeId( String apiKey, String assigneeId ) {
		
		boolean isSuccess = false;
		HashMap<String, Object> returnHashMap = new HashMap<String, Object>();
		
		try {
			
			String responseString = UrlFetchUtil.httpRequest( Urls.getReadTasksByAssigneeIdUrl( apiKey, assigneeId ), null, "GET",  "application/json", null, 2);
			
			JSONObject responseJson = new JSONObject( responseString );
			
			if( responseJson.has("status") && (boolean) responseJson.get("status") ) {
				
				HashMap<String, Object> hashMapOfTasks =  FinalVariables.getObjectMapper().readValue( String.valueOf( responseJson.get("tasks") ), new TypeReference< HashMap<String, Object> >() {});
				
				ArrayList<Task> arrayListOfTask = new ArrayList<Task>();
				
				Iterator iterator = hashMapOfTasks.entrySet().iterator();
			    while ( iterator.hasNext() ) {
			    	
			        Map.Entry pairs = (Map.Entry) iterator.next();
			        
			        TaskTemp taskTemp = new TaskTemp();
			        BeanUtils.populate( taskTemp, (Map) pairs.getValue() );
			        taskTemp.setID( String.valueOf( pairs.getKey() ) );
			        Task task = TaskTempAndTaskConverter.taskTempToTask( taskTemp );
			        arrayListOfTask.add( task );
			        
			        iterator.remove(); 													// Avoids a ConcurrentModificationException
			        
			    }
				
				returnHashMap.put("tasks", arrayListOfTask);
				
				isSuccess = true;
				
			}
			
		} catch (Exception e) {
			
			logger.log(Level.SEVERE, "Error Path : " + TaskFactory.class.getPackage().getName() + "; Method : createTask(); Detail : Error while creating a task.");
			logger.log(Level.SEVERE, e.getMessage(), e);
			
		} finally {
			returnHashMap.put("success", isSuccess);
		}
		
		return returnHashMap;
		
	}

	public static HashMap<String, Object> getTasksByOwnerId( String apiKey, String userId, String ownerId ) {
	
		boolean isSuccess = false;
		HashMap<String, Object> returnHashMap = new HashMap<String, Object>();
		
		try {
			
			String responseString = UrlFetchUtil.httpRequest( Urls.getReadTasksByOwnerIdUrl( apiKey, userId, ownerId ), null, "GET",  "application/json", null, 2);
			
			JSONObject responseJson = new JSONObject( responseString );
			
			if( responseJson.has("success") && (boolean) responseJson.get("success") ) {
				
				HashMap<String, Object> hashMapOfTasks =  FinalVariables.getObjectMapper().readValue( String.valueOf( responseJson.get("tasks") ), new TypeReference< HashMap<String, Object> >() {});				
				
				ArrayList<Task> arrayListOfTask = new ArrayList<Task>();
				
				Iterator iterator = hashMapOfTasks.entrySet().iterator();
			    while ( iterator.hasNext() ) {
			    	
			        Map.Entry pairs = (Map.Entry) iterator.next();
			        
			        TaskTemp taskTemp = (TaskTemp) pairs.getValue();
			        Task task = TaskTempAndTaskConverter.taskTempToTask( taskTemp );
			        arrayListOfTask.add( task );
			        
			        iterator.remove(); 													// Avoids a ConcurrentModificationException
			        
			    }
				
				returnHashMap.put("tasks", arrayListOfTask);
				
				isSuccess = true;
				
			}
			
		} catch (Exception e) {
			
			logger.log(Level.SEVERE, "Error Path : " + TaskFactory.class.getPackage().getName() + "; Method : createTask(); Detail : Error while creating a task.");
			logger.log(Level.SEVERE, e.getMessage(), e);
			
		} finally {
			returnHashMap.put("success", isSuccess);
		}
		
		return returnHashMap;
		
	}
	
	public static HashMap<String, Object> deleteTask( String apiKey, String userId, Task taskToDelete ) {
		
		boolean isSuccess = false;
		HashMap<String, Object> returnHashMap = new HashMap<String, Object>();
		
		try {
			
			TaskTemp taskTempToDelete = TaskTempAndTaskConverter.taskToTaskTemp( taskToDelete );
			
			JSONObject taskTempToCreateJson = new JSONObject( taskTempToDelete );
			
			String responseString = UrlFetchUtil.httpRequest( Urls.getDeleteTaskUrl( apiKey, userId ), String.valueOf( taskTempToCreateJson ), "POST",  "application/json", null, 2);
			
			JSONObject responseJson = new JSONObject( responseString );
			
			if( responseJson.has("status") && (boolean) responseJson.get("status") ) {
				
				TaskTemp deletedTaskTemp = FinalVariables.getObjectMapper().readValue( String.valueOf( responseJson.get("task") ), new TypeReference<TaskTemp>() {});
				
				Task deletedTask = TaskTempAndTaskConverter.taskTempToTask( deletedTaskTemp );
				
				returnHashMap.put("task", deletedTask);
				
				isSuccess = true;
				
			}
			
		} catch (Exception e) {
			
			logger.log(Level.SEVERE, "Error Path : " + TaskFactory.class.getPackage().getName() + "; Method : createTask(); Detail : Error while creating a task.");
			logger.log(Level.SEVERE, e.getMessage(), e);
			
		} finally {
			returnHashMap.put("success", isSuccess);
		}
		
		return returnHashMap;
		
	}

}
