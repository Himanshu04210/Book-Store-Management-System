package com.masai.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.DTO.LongInCrede;
import com.masai.Exception.UserException;
import com.masai.Model.User;
import com.masai.Service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/user")
	public ResponseEntity<User> registerUser(@RequestBody User user) throws UserException {
		User savedUser = userService.RegisterUser(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}

	@GetMapping("/user/{key}") 
	public ResponseEntity<List<User>> getAllUser(@PathVariable String key) throws UserException {

		List<User> users = userService.getAllUsers(key);

		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	
	@PostMapping("login")
	public ResponseEntity<String> logIn(@RequestBody LongInCrede crede) throws UserException {
		String key = userService.logIn(crede);
		return new ResponseEntity<>(key, HttpStatus.FOUND);
	}
	

	@PostMapping("/logout")
	public String logoutCustomer(@RequestParam(required = false) String key) throws UserException {
		return userService.logOutFromAccount(key);
		
	}
}
