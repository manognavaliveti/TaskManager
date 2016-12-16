package com.todo.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.todo.model.User;
@Repository
@Transactional
public class UserDaoimpl implements UserDao {
@Autowired
SessionFactory sessionFactory;
	public void addUser(User user) {
		Session session=sessionFactory.getCurrentSession();
		session.save(user);
		
	}

	public void updateUser(User user) {
		Session session=sessionFactory.getCurrentSession();
		session.update(user);
		
	}

	public List<User> viewUser() {
	Session session=sessionFactory.getCurrentSession();
	List<User> user=session.createCriteria(User.class).list();
		return user;
	}

	public int validateUser(String name, String password) {
		
			
			int res=0;
			Session session=sessionFactory.getCurrentSession();
			Query result=session.createQuery("from User u where u.name='"+name+"'");
			  
			List<User> user=result.list();
			System.out.println("user:"+user);
		if(user.size()==0)
		{
			res=0;
		}
		else
		{
			for(int i=0;i<user.size();i++)
			{
				System.out.println("inside for loop");
				String dataName=user.get(i).getName();
				String datapassword=user.get(i).getPassword();
				
				if(dataName.equals(name)&&datapassword.equals(password))
				{
					res=1;
					System.out.println("the result is:"+res);
				}
				
				}
		}	
		return res;
		}
		

}
