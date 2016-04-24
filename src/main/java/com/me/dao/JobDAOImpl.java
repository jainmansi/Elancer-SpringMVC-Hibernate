package com.me.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.exception.AdException;
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
	
	public ArrayList<Job> findByUserId(long l) throws AdException {
        DAO.begin();
		try {
            Query q = DAO.getSession().createQuery("from Job where clientId = :clientId");
            q.setLong("clientId", l);
            ArrayList<Job> list = (ArrayList<Job>) q.list();
            DAO.commit();
            return list;
        } catch (HibernateException e) {
            DAO.rollback();
            throw new AdException("Could not get Job for" + l , e);
        }
    }
	
	public Job findById(int i) throws AdException {
        DAO.begin();
		try {
            Query q = DAO.getSession().createQuery("from Job where jobId = :id");
            q.setLong("id", i);
            Job job = (Job) q.uniqueResult();
            DAO.commit();
            return job;
        } catch (HibernateException e) {
            DAO.rollback();
            throw new AdException("Could not get Job for" + i , e);
        }
    }
	
	public ArrayList<Job> findByKeyword(String keyword, int catid) throws AdException {
        DAO.begin();
		try {
            Query q = DAO.getSession().createQuery("from Job where jobDescription LIKE :keyword AND categoryId = :catid");
            q.setString("keyword", "%"+keyword+"%");
            q.setInteger("catid", catid);
            ArrayList<Job> list = (ArrayList<Job>) q.list();
            DAO.commit();
            return list;
        } catch (HibernateException e) {
            DAO.rollback();
            throw new AdException("Could not get Job for" + keyword , e);
        }
    }

}
