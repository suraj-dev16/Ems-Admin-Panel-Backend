package com.example.AdminPanelBackend.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.AdminPanelBackend.Entity.User;
import com.example.AdminPanelBackend.Exception.ResourceNotFoundException;

public interface UserService {
	
	//CRUD operations
	//create
	public User createUserService(User user);
	
	//read
	public List<User> getAllUserService();
	
	//read one user by id
	public User getUserByIdService(int id)throws ResourceNotFoundException ;
	
	//update
	public User updateUserService(int id,User user)throws ResourceNotFoundException;
	
	//delete
	public void deleteUserService(int id)throws ResourceNotFoundException;
	
	//getAllpage Count
	public  Page<User> userPageCount(Pageable pageable);
	
	public List<User> getAllUserByIdDesc();

}
