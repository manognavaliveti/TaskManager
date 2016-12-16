package com.todo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.todo.model.Tasks;
@Repository
@Transactional
public class TasksDaoimpl implements TasksDao {
@Autowired
SessionFactory sessionFactory;
	public void addTasks(Tasks task) {
		Session session=sessionFactory.getCurrentSession();
		session.save(task);
		
	}

	public void updateTasks(Tasks task) {
		Session session=sessionFactory.getCurrentSession();
		session.update(task);
		
	}

	

	public void deleteTasks(int id) {
		Session session=sessionFactory.getCurrentSession();
		Tasks task=(Tasks)session.get(Tasks.class,new Integer(id));
		session.delete(task);
	}
	public List<Tasks> viewMyTasks(String tasksOf) {
		System.out.println("heyy viewing my tasks...!!!!!!!!");
		Session session =sessionFactory.getCurrentSession();
		Criteria cr=session.createCriteria(Tasks.class);
		cr.add(Restrictions.eq("tasksOf",tasksOf));
		cr.add(Restrictions.eq("status",false));
		List list=cr.list();
		System.out.println("list:"+list);
		return list;
}
	public List<Tasks> viewMyTask(String tasksOf) {
		System.out.println("heyy viewing my tasks...!!!!!!!!");
		Session session =sessionFactory.getCurrentSession();
		Criteria cr=session.createCriteria(Tasks.class);
		cr.add(Restrictions.eq("tasksOf",tasksOf));
		cr.add(Restrictions.eq("status",true));
		List list=cr.list();
		System.out.println("list:"+list);
		return list;
	
	}

}
