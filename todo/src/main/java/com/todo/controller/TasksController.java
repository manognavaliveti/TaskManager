package com.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todo.dao.TasksDao;
import com.todo.model.Tasks;


@RestController
public class TasksController {
	@Autowired
	TasksDao tasksDao;
	@RequestMapping(value="/addTasks",headers="Accept=application/json",method=RequestMethod.POST)
	public void addTasks(@RequestBody Tasks task){
		tasksDao.addTasks(task);
		
	}
	
	@RequestMapping(value="/updateTasks",headers="Accept=application/json",method=RequestMethod.PUT)
	public void updateTasks(@RequestBody Tasks task)
	{
		System.out.println("Inside update task");
		tasksDao.updateTasks(task);
	}
	@RequestMapping(value="/deleteTasks/{id}",headers="Accept=application/json",method=RequestMethod.DELETE)
	public void deleteTasks(@PathVariable int id)
	{
		tasksDao.deleteTasks(id);
	}

	@RequestMapping(value="/viewMyTasks/{tasksOf}",headers="Accept=application/json",method=RequestMethod.GET)
	public List<Tasks> viewMyTasks(@PathVariable("tasksOf")String tasksOf){
		System.out.print("given name:"+tasksOf);
	    return tasksDao.viewMyTasks(tasksOf);
		}
	@RequestMapping(value="/viewMyTask/{tasksOf}",headers="Accept=application/json",method=RequestMethod.GET)
	public List<Tasks> viewMyTask(@PathVariable("tasksOf")String tasksOf){
		System.out.print("given name:"+tasksOf);
	    return tasksDao.viewMyTask(tasksOf);
		}
}
