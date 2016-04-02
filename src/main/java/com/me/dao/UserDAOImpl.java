package com.me.dao;

import java.math.BigDecimal;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.exception.AdException;
import com.me.pojo.User;

public class UserDAOImpl extends GenericDAOImpl<User, BigDecimal> implements UserDAO {
	
	public UserDAOImpl(){
		
	}

	public User findByUsername(String username) throws AdException{
		DAO dao = new DAO();
		dao.begin();
		try {
            Query q = getSession().createQuery("from User where name = :username");
            q.setString("username", username);
            User user = (User) q.uniqueResult();
            dao.commit();
            return user;
        } catch (HibernateException e) {
            dao.rollback();
            throw new AdException("Could not get user " + username, e);
        } finally{
        	DAO.close();
        }
	}
	
	public User save(String username) throws AdException{
		DAO dao = new DAO();
		dao.begin();
		try {
            Query q = getSession().createQuery("from User where name = :username");
            q.setString("username", username);
            User user = (User) q.uniqueResult();
            dao.commit();
            return user;
        } catch (HibernateException e) {
            dao.rollback();
            throw new AdException("Could not get user " + username, e);
        } finally{
        	DAO.close();
        }
	}

}
