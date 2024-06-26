package com.example.AdminPanelBackend.Controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.AdminPanelBackend.Entity.User;
import com.example.AdminPanelBackend.Exception.ResourceNotFoundException;
import com.example.AdminPanelBackend.Repository.UserRepository;
import com.example.AdminPanelBackend.ServiceImpl.UserServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/user")
public class UserController {
	private static final Logger LOGGER = LogManager.getLogger(UserController.class);

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	
	@PostMapping
	public ResponseEntity<User> createUserController(@RequestBody User user) {
		LOGGER.info("Invoking createUserController method ||Request-User :" + user);

		User u = userServiceImpl.createUserService(user);

		LOGGER.info("Invoking createUserController method get User from  Service ||User :" + u);

		return new ResponseEntity<>(u, HttpStatus.CREATED);
	}
	//get all users details with pagination 
	@GetMapping()
	public ResponseEntity<List<User>> getAllUserController(@RequestParam("start") int start, @RequestParam("limit") int limit) {
        int pageIndex = start / limit; // Calculate the page index based on start and limit
		Pageable pageable = PageRequest.of(pageIndex, limit);
		//get all pages 
        Page<User> pageResult = userServiceImpl.userPageCount(pageable);
        List<User> users = pageResult.getContent();
        LOGGER.info("Invoking pageResult getContent method ||List-User :" + users);
//		List<User> users = userServiceImpl.getAllUserService();

//		LOGGER.info("Invoking getAllUserController method ||List-User :" + users);
		
        return new ResponseEntity<>(users, HttpStatus.OK);
	}
	@GetMapping("/wotPage")
	public ResponseEntity<List<User>> getEmployeeWithoutPagination() {
		List<User> users =  userServiceImpl.getAllUserService();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserByIdController(@PathVariable int id) throws ResourceNotFoundException {
		User u = userServiceImpl.getUserByIdService(id);
		
		LOGGER.info("Invoking getUserByIdController method ||User :" + u + " and |id :" + id);

		return ResponseEntity.status(HttpStatus.OK).body(u);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUserController(@PathVariable int id) throws ResourceNotFoundException {
		User user = userServiceImpl.getUserByIdService(id);
		
		LOGGER.info("Invoking deleteUserController method ||User :" + user + " and |id : " + id);
		if (user != null) {
			userServiceImpl.deleteUserService(id);
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUserController(@PathVariable int id, @RequestBody User user) throws ResourceNotFoundException {
		LOGGER.info("Invoking updateUserController method ||Request-User :" + user + " and |id :" + id);
		
		User updatedUser = userServiceImpl.updateUserService(id, user);
		
		LOGGER.info("Invoking updateUserController method ||User :" + updatedUser);
		
		if (updatedUser != null) {
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	//get all user count 
	@GetMapping("/count")
	public ResponseEntity<Long> getAllUserCount(){
		List<User> users = userServiceImpl.getAllUserService();
		Long userCount =  (long) users.size();
		return ResponseEntity.status(HttpStatus.OK).body(userCount);
//		 long count = userRepository.count();
//	        return new ResponseEntity<>(count, HttpStatus.OK);
	}
	
	@GetMapping("/topUser")
	public ResponseEntity<List<User>> getTopUserForDashBoard(){
		List<User> users = userServiceImpl.getAllUserByIdDesc();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	
	

}
