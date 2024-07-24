package com.arvind.testproject.service;

import java.util.List;

import com.arvind.testproject.entity.User;

public interface IUserService {
	
	public User createUser(User user);

    public void deleteUser(Long id);

    public List<User> getAllUsers();

	public User findByUsername(String string);
}
