package com.adaptavant.workwidget.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adaptavant.workwidget.constants.FinalVariables;
import com.adaptavant.workwidget.factories.TaskFactory;
import com.adaptavant.workwidget.dto.Task;
import com.adaptavant.workwidget.dto.TaskTemp;
import com.adaptavant.workwidget.services.TaskService;
import com.adaptavant.workwidget.utils.TaskTempAndTaskConverter;

@Controller
@RequestMapping("/")
public class TaskController {
	
	@RequestMapping(value="task", method = RequestMethod.POST)
	public @ResponseBody Task createTask( @RequestBody String requestBody, @RequestParam("apiKey") String apiKey, @RequestParam("userId") String userId ) {
		
		return TaskService.createTask(apiKey, userId, requestBody);
		
	}
	
	@RequestMapping(value="task", method = RequestMethod.PUT)
	public @ResponseBody HashMap<String, Object> updateTask( @RequestBody String requestBody, @RequestParam("apiKey") String apiKey, @RequestParam("userId") String userId ) {
		
		return TaskService.updateTask( apiKey, userId, requestBody);
		
	}
	
	@RequestMapping(value="getTasksByAssigneeId", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Task> readTasks( @RequestParam("apiKey") String apiKey, @RequestParam("userId") String userId) {
		
		return TaskService.getTasksByAssigneeId( apiKey, userId);
	
	}

}
