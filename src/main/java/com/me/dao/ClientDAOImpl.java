package com.me.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.exception.AdException;
import com.me.pojo.Client;
public class ClientDAOImpl implements ClientDAO{

	public Client findByUsername(String username) throws AdException {
		DAO.begin();
		try {
            Query q = DAO.getSession().createQuery("from Client where username = :username");
            q.setString("username", username);
            Client client = (Client) q.uniqueResult();
            DAO.commit();
            return client;
        } catch (HibernateException e) {
            DAO.rollback();
            throw new AdException("Could not get user " + username, e);
        } finally{
        	DAO.close();
        }
	}

	public void save(Client client) throws Exception {
		DAO.begin();
		try {
	            DAO.getSession().save(client);
	            DAO.commit();
	        } catch (HibernateException e) {
	            DAO.rollback();
	            throw new Exception("Could not get client " + e.getMessage());
	        } finally{
	        	DAO.close();
	        }
	}

}
