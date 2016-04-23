package com.me.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.exception.AdException;
import com.me.pojo.JobCategory;
import com.me.pojo.Person;

public class JobCategoryDAOImpl implements JobCategoryDAO{

	public void save(JobCategory jobCategory) throws Exception {
		DAO.begin();
		try {
	            DAO.getSession().save(jobCategory);
	            DAO.commit();
	        } catch (HibernateException e) {
	            DAO.rollback();
	            throw new Exception("Could not get job category " + e.getMessage());
	        } finally{
	        	DAO.close();
	        }		
	}
	
	public ArrayList<JobCategory> findAll() throws Exception {
		DAO.begin();
        try {
            Query q = DAO.getSession().createQuery("from JobCategory");
            ArrayList<JobCategory> list = (ArrayList<JobCategory>) q.list();
            DAO.commit();
            return list;
        } catch (HibernateException e) {
            DAO.rollback();
            throw new Exception("Could not list the categories", e);
        }
    }
	
	public JobCategory findById(int categoryId) throws AdException {
        DAO.begin();
		try {
            Query q = DAO.getSession().createQuery("from JobCategory where categoryId = :categoryId");
            q.setInteger("categoryId", categoryId);
            JobCategory jobCatgory = (JobCategory) q.uniqueResult();
            DAO.commit();
            return jobCatgory;
        } catch (HibernateException e) {
            DAO.rollback();
            throw new AdException("Could not get Job Category " + categoryId , e);
        }
    }

}
