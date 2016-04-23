package com.me.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.pojo.JobCategory;

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

}
