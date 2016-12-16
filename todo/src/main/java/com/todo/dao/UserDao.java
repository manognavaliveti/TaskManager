package com.todo.dao;

import java.util.List;

import com.todo.model.User;

public interface UserDao {
	void addUser(User user);
	void updateUser(User user);
	List<User> viewUser();
	int validateUser(String name,String password);

}
