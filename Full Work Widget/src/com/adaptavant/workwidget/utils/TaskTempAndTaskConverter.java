package com.adaptavant.workwidget.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.adaptavant.workwidget.dto.TaskTemp;
import com.adaptavant.workwidget.dto.Task;
import com.adaptavant.workwidget.services.TaskService;


public class TaskTempAndTaskConverter {
	
	private static Logger logger = Logger.getLogger( TaskService.class.getPackage().getName());

	public static Task taskTempToTask ( TaskTemp taskTemp ) {
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		Date dueDate = null;
		
		try {
			if( taskTemp.getDueDate() != null ) {
				dueDate = dateFormat.parse( taskTemp.getDueDate() );
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error Path : " + TaskService.class.getPackage().getName() + "; Method : taskTempToTask(); Detail : Error while parsing String Date to Date Object.");
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
		
		Task returnTask = new Task(taskTemp.getID(), taskTemp.getType(), taskTemp.getStatus(), dueDate, taskTemp.getComments(), taskTemp.getNotes(), taskTemp.getCreatedDate(), taskTemp.getAssigneeID(), taskTemp.getOwnerID(), taskTemp.getLinkedAccounts(), taskTemp.getLinkedContacts(), taskTemp.getLinkedDeals(), taskTemp.getLinkedSubscribers(), taskTemp.getLinkedTags(), taskTemp.getLinkedTasks(), taskTemp.getLinkedDocuments(), taskTemp.getTimeZone(), taskTemp.getAccountID(), taskTemp.getRating());
		
		return returnTask;
		
	}
	
	public static TaskTemp taskToTaskTemp( Task task) {
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
	    String dueDateShortString = dateFormat.format( task.getDueDate() );
	    TaskTemp taskTemp = new TaskTemp(task.getID(), task.getType(), task.getStatus(), dueDateShortString, task.getComments(), task.getNotes(), task.getCreatedDate(), task.getAssigneeID(), task.getOwnerID(), task.getLinkedAccounts(), task.getLinkedContacts(), task.getLinkedDeals(), task.getLinkedSubscribers(), task.getLinkedTags(), task.getLinkedTasks(), task.getLinkedDocuments(), task.getTimeZone(), task.getAccountID(), task.getRating());
		return taskTemp;
		
	}
	
}
