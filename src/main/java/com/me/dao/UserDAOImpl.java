package com.me.dao;

import java.math.BigDecimal;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.exception.AdException;
import com.me.pojo.User;

public class UserDAOImpl implements UserDAO {
	
	public UserDAOImpl(){
		
	}

	public User findByUsername(String username) throws AdException{
		DAO.begin();
		try {
            Query q = DAO.getSession().createQuery("from User where name = :username");
            q.setString("username", username);
            User user = (User) q.uniqueResult();
            DAO.commit();
            return user;
        } catch (HibernateException e) {
            DAO.rollback();
            throw new AdException("Could not get user " + username, e);
        } finally{
        	DAO.close();
        }
	}
	
	public void save(User user) throws Exception{
		DAO.begin();
		try {
			DAO.begin();
	            DAO.getSession().save(user);
	            DAO.commit();
	        } catch (HibernateException e) {
	            DAO.rollback();
	            throw new Exception("Could not get person " + e.getMessage());
	        } finally{
	        	DAO.close();
	        }
		
	}

}
