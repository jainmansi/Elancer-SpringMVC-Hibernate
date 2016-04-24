package com.me.dao;

import java.util.ArrayList;

import com.me.exception.AdException;
import com.me.pojo.JobApplication;

public interface ApplicationDAO {
	public void save(JobApplication jobApplication) throws Exception;
	public ArrayList<JobApplication> findById(int id) throws AdException;
	public JobApplication findByApplicationId(int id) throws AdException;
}
