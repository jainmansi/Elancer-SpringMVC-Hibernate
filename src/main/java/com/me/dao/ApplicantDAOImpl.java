package com.me.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.me.exception.AdException;
import com.me.pojo.Applicant;

public class ApplicantDAOImpl implements ApplicantDAO{

	public Applicant findByUsername(String username) throws AdException {
		DAO.begin();
		try {
            Query q = DAO.getSession().createQuery("from Applicant where username = :username");
            q.setString("username", username);
            Applicant applicant = (Applicant) q.uniqueResult();
            DAO.commit();
            return applicant;
        } catch (HibernateException e) {
            DAO.rollback();
            throw new AdException("Could not get user " + username, e);
        } finally{
        	DAO.close();
        }
	}

	public void save(Applicant applicant) throws Exception {
		DAO.begin();
		try {
			DAO.begin();
	            DAO.getSession().save(applicant);
	            DAO.commit();
	        } catch (HibernateException e) {
	            DAO.rollback();
	            throw new Exception("Could not get person " + e.getMessage());
	        } finally{
	        	DAO.close();
	        }
	}

}
