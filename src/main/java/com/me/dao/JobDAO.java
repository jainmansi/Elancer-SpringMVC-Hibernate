package com.me.dao;

import java.util.ArrayList;

import com.me.exception.AdException;
import com.me.pojo.Job;

public interface JobDAO {
	public void save(Job job) throws Exception;
	public ArrayList<Job> findByUserId(long l) throws AdException;
	public ArrayList<Job> findByKeyword(String keyword, int catid) throws AdException;
}