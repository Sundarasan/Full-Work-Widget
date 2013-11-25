package com.adaptavant.workwidget.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.codehaus.jackson.type.TypeReference;

import com.adaptavant.workwidget.constants.FinalVariables;
import com.adaptavant.workwidget.factories.TaskFactory;
import com.adaptavant.workwidget.dto.Task;
import com.adaptavant.workwidget.dto.TaskTemp;
import com.adaptavant.workwidget.utils.TaskTempAndTaskConverter;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.JsonObject;


public class TaskService {
	
	private static Logger logger = Logger.getLogger( TaskService.class.getPackage().getName());
	
	public static Task createTask( String apiKey, String userId, String taskRequestBody ) {
		
		boolean isSuccess = false;
		//HashMap<String, Object> returnHashMap = new HashMap<String, Object>();
		Task returnTask = null;

		try {
			
			JSONObject requestBodyJson = new JSONObject( taskRequestBody );
			
			TaskTemp taskTemp_ToCreate = FinalVariables.getObjectMapper().readValue(  String.valueOf(requestBodyJson), new TypeReference<TaskTemp>() {} );
			
			Task task_ToCreate = TaskTempAndTaskConverter.taskTempToTask( taskTemp_ToCreate );

			HashMap<String, Object> responseFromFactory = TaskFactory.createTask( apiKey, userId, task_ToCreate);
			
			if( responseFromFactory.containsKey("success") && (boolean) responseFromFactory.get("success") ) {
				
				Task task = (Task) responseFromFactory.get("task");
				returnTask = task;
				//returnHashMap.put("task", task);
				isSuccess = true;
				
			}
			
		} catch(Exception e) {
			
			System.out.println("Exception");
			e.printStackTrace();
			
		} finally {
			//returnHashMap.put("success", isSuccess);
		}
		
		return returnTask;
		
	}
	
	public static ArrayList<Task> getTasksByAssigneeId( String apiKey, String userId) {
		
		ArrayList<Task> returnArrayListOfTask = new ArrayList<Task>();
		
		try {
			
			HashMap<String, Object> hashMapOfTasks = TaskFactory.getTasksByAssigneeId(apiKey, userId);
			
			if( hashMapOfTasks.containsKey("success") && (boolean) hashMapOfTasks.get("success") ) {
				
				returnArrayListOfTask = (ArrayList<Task>) hashMapOfTasks.get("tasks");
				
			}
			
		} catch (Exception e) {
			
			logger.log(Level.SEVERE, "Error Path : " + TaskService.class.getPackage().getName() + "; Method : getTasks(); Detail : Error while getting the tasks.");
			logger.log(Level.SEVERE, e.getMessage(), e);
			
		}
		
		return returnArrayListOfTask;
	}
	
	public static HashMap<String, Object> updateTask( String apiKey, String userId, String taskRequestBody ) {
		
		HashMap<String, Object> returnHashMap = new HashMap<String, Object>();
		boolean isSuccess = false;
		
		try {
			
			TaskTemp taskTemp_ToUpdate = FinalVariables.getObjectMapper().readValue( taskRequestBody , new TypeReference<TaskTemp>() {} );
			Task task_ToUpdate = TaskTempAndTaskConverter.taskTempToTask( taskTemp_ToUpdate );
			
			HashMap<String, Object> responseFromTaskFactory = TaskFactory.updateTask(apiKey, userId, task_ToUpdate);
			
			if( responseFromTaskFactory.containsKey("success") && (boolean) responseFromTaskFactory.get("success") ) {
				
				returnHashMap.put( "task", (Task) responseFromTaskFactory.get("task") );
				isSuccess = true;
				
			}
			
		} catch( Exception e ) {
			
			logger.log(Level.SEVERE, "Error Path : " + TaskService.class.getPackage().getName() + "; Method : updateTasks(); Detail : Error while updating the task.");
			logger.log(Level.SEVERE, e.getMessage(), e);
			
		} finally {
			
			returnHashMap.put( "success", isSuccess );
			
		}
		
		return returnHashMap;
		
	}

}
