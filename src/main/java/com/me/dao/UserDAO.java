package com.me.dao;

import java.math.BigDecimal;

import com.me.exception.AdException;
import com.me.pojo.User;

public interface UserDAO {
	 public User findByUsername(String username) throws AdException;
	 public void save(User user) throws Exception;
}
