package com.todo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tasks {
	@Id@GeneratedValue
private int taskId;
private String taskName;
private String description;
private String lastDate;
private String tasksOf;
private boolean status;

public boolean isStatus() {
	return status;
}
public void setStatus(boolean status) {
	this.status = status;
}
public String getTasksOf() {
	return tasksOf;
}
public void setTasksOf(String tasksOf) {
	this.tasksOf = tasksOf;
}
public int getTaskId() {
	return taskId;
}
public void setTaskId(int taskId) {
	this.taskId = taskId;
}
public String getTaskName() {
	return taskName;
}
public void setTaskName(String taskName) {
	this.taskName = taskName;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getLastDate() {
	return lastDate;
}
public void setLastDate(String lastDate) {
	this.lastDate = lastDate;
}

}
