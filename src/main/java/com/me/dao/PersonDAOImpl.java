package com.me.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.exception.AdException;
import com.me.pojo.Person;
import com.me.pojo.User;

public class PersonDAOImpl implements PersonDAO{

	public PersonDAOImpl(){
		
	}
	
	public void save(Person person) throws AdException{
		DAO dao = new DAO();
		dao.begin();
		try {
            DAO.getSession().save(person);
            dao.commit();
        } catch (HibernateException e) {
            dao.rollback();
            throw new AdException("Could not get person " + e.getMessage());
        } finally{
        	DAO.close();
        }
	}
}
