package com.me.dao;

import java.math.BigDecimal;

import com.me.exception.AdException;
import com.me.pojo.Person;
import com.me.pojo.User;

public interface UserDAO extends GenericDAO<User, BigDecimal> {
	 public User findByUsername(String username) throws AdException;
}
