package com.me.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.exception.AdException;
import com.me.pojo.JobApplication;
import com.me.pojo.JobCategory;

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
	
	public ArrayList<JobApplication> findById(int id) throws AdException {
        DAO.begin();
		try {
            Query q = DAO.getSession().createQuery("from JobApplication where jobId = :id");
            q.setInteger("id", id);
            ArrayList<JobApplication> applicationList = (ArrayList<JobApplication>) q.list();
            DAO.commit();
            return applicationList;
        } catch (HibernateException e) {
            DAO.rollback();
            throw new AdException("Could not get Job Application " + id , e);
        }
    }

}
