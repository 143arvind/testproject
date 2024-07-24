package com.arvind.testproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.arvind.testproject.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
    User findByUsername(String username);

}
