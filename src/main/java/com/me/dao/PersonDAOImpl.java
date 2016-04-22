package com.me.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.exception.AdException;
import com.me.pojo.Person;

public class PersonDAOImpl implements PersonDAO{

	public PersonDAOImpl(){
	}
	
	public Person findByUsername(String username) throws AdException {
        DAO.begin();
		try {
            Query q = DAO.getSession().createQuery("from Person where username = :username");
            q.setString("username", username);
            Person person = (Person) q.uniqueResult();
            DAO.commit();
            return person;
        } catch (HibernateException e) {
            DAO.rollback();
            throw new AdException("Could not get person " + username, e);
        }
    }
	
	public void save(Person person) throws AdException{
		DAO.begin();
		try {
            DAO.getSession().save(person);
            DAO.commit();
        } catch (HibernateException e) {
            DAO.rollback();
            throw new AdException("Could not get person " + e.getMessage());
        } finally{
        	DAO.close();
        }
	}
}
