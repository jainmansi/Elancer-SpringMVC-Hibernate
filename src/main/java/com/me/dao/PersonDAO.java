package com.me.dao;

import java.math.BigDecimal;

import com.me.exception.AdException;
import com.me.pojo.Person;
import com.me.pojo.User;

public interface PersonDAO {
	public void save(Person person) throws AdException;
}
