package com.arvind.testproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arvind.testproject.entity.User;
import com.arvind.testproject.repository.UserRepository;
@Service
public class UserServiceImpl implements IUserService {
	
	 @Autowired
	 private UserRepository userRepository;
	 
	 @Override
	public User createUser(User user) {
        return userRepository.save(user);
    }
	 
	 @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
	 
	 @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
	 
	 public User findByUsername(String username) {
	        return userRepository.findByUsername(username);
	    }

}
