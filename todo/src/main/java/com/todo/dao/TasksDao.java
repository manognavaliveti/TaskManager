package com.todo.dao;

import java.util.List;

import com.todo.model.Tasks;

public interface TasksDao {
	void addTasks(Tasks task);
	void updateTasks(Tasks task);
	
	void deleteTasks(int id);
	List<Tasks> viewMyTasks(String tasksOf);
	
	List<Tasks> viewMyTask(String tasksOf);
}
