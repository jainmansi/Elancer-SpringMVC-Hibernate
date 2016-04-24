package com.me.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.exception.AdException;
import com.me.pojo.Client;
import com.me.pojo.Person;
public class ClientDAOImpl implements ClientDAO{

	public Client findById(long id) throws AdException {
		DAO.begin();
		System.out.println(id + "Client DAO");
		try {
            Query q = DAO.getSession().createQuery("from Client where personID = :id");
            q.setLong("id", id);
            Client client = (Client) q.uniqueResult();
            DAO.commit();
            System.out.println(client.getFirstName());
            return client;
        } catch (HibernateException e) {
            DAO.rollback();
            throw new AdException("Could not get user " + id, e);
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
