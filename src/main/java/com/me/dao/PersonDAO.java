package com.me.dao;

import com.me.exception.AdException;
import com.me.pojo.Person;

public interface PersonDAO {
	public Person findByUsername(String username) throws AdException;
	public void save(Person person) throws AdException;
}
