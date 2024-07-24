package com.arvind.testproject.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arvind.testproject.entity.User;
import com.arvind.testproject.service.IUserService;

@RestController
@RequestMapping("/api/users")
public class UserControler {
	    @Autowired
	    private IUserService userService;

	    @PreAuthorize("hasRole('ADMIN')")
	    @PostMapping
	    public User createUser(@RequestBody User user) {
	        return userService.createUser(user);
	    }

	    @PreAuthorize("hasRole('ADMIN')")
	    @DeleteMapping("/{id}")
	    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
	        userService.deleteUser(id);
	        return ResponseEntity.ok().build();
	    }
	    
	    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	    @GetMapping
	    public List<User> getAllUsers() {
	        return userService.getAllUsers();
	    }
}
