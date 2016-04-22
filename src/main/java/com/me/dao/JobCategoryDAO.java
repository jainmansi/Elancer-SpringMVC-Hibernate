package com.me.dao;

import java.util.List;

import com.me.pojo.JobCategory;

public interface JobCategoryDAO {
	public void save(JobCategory jobCategory) throws Exception;
	public List<JobCategory> findAll() throws Exception;
}
