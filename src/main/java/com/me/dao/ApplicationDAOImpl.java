package com.me.dao;

import org.hibernate.HibernateException;

import com.me.pojo.JobApplication;

public class ApplicationDAOImpl implements ApplicationDAO{

	@Override
	public void save(JobApplication jobApplication) throws Exception {
		DAO.begin();
		try {
	            DAO.getSession().save(jobApplication);
	            DAO.commit();
	        } catch (HibernateException e) {
	            DAO.rollback();
	            throw new Exception("Could not get job category " + jobApplication.getApplicationId() + e.getMessage());
	        } finally{
	        	DAO.close();
	        }
	}

}
