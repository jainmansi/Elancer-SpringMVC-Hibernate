package com.me.dao;

import com.me.exception.AdException;
import com.me.pojo.Client;

public interface ClientDAO {
	public Client findByUsername(String username) throws AdException;
	 public void save(Client client) throws Exception;
}
