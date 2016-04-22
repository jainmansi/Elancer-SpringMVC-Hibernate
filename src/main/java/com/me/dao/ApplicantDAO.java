package com.me.dao;

import com.me.exception.AdException;
import com.me.pojo.Applicant;

public interface ApplicantDAO {

	public Applicant findByUsername(String username) throws AdException;
	public void save(Applicant applicant) throws Exception;
}
