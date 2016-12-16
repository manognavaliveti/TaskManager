package com.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todo.dao.UserDao;
import com.todo.model.User;

@RestController
public class UserController {
@Autowired
UserDao userDao;
@RequestMapping(value="/addUser",headers="Accept=application/json",method=RequestMethod.POST)
public void addUser(@RequestBody User user){
	userDao.addUser(user);
	
}
@RequestMapping(value="/authenticate", method=RequestMethod.POST,headers="Accept=application/json")
public int authenticateUser(@RequestBody User user)
{
	 System.out.println("name:"+user.getName());
	 System.out.println("password:"+user.getPassword());
int result=0;
	 result=userDao.validateUser(user.getName(),user.getPassword());
	 System.out.println("result is:"+result);
	 return result;
}

}
