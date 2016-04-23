package com.me.dao;

import org.hibernate.HibernateException;

import com.me.pojo.Job;

public class JobDAOImpl implements JobDAO{

	public void save(Job job) throws Exception {
		DAO.begin();
		try {
	            DAO.getSession().save(job);
	            DAO.commit();
	        } catch (HibernateException e) {
	            DAO.rollback();
	            throw new Exception("Could not get job " + e.getMessage());
	        } finally{
	        	DAO.close();
	        }
	}

}
