package com.example.AdminPanelBackend.ServiceImpl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.AdminPanelBackend.Entity.User;
import com.example.AdminPanelBackend.Exception.ResourceNotFoundException;
import com.example.AdminPanelBackend.Repository.CustomHQLRepositoryImpl;
import com.example.AdminPanelBackend.Repository.UserRepository;
import com.example.AdminPanelBackend.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CustomHQLRepositoryImpl customRepo;


	@Override
	public User createUserService(User user) {
		// TODO Auto-generated method stub
		LOGGER.info("Under UserServiceImpl class | |invoking createUser method ||User : " + user);

		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUserService() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User getUserByIdService(int id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub

		LOGGER.debug("Under UserServiceImpl class | |invoking getUserbyId method ||id : " + id);
		// return userRepository.findById(id).orElse(null);

		User u = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee" + " not found for this id :: " + id));
		LOGGER.debug("Under UserServiceImpl class | |invoking getUserById method ||Optional-User : " + u);

		LOGGER.debug("Under UserServiceImpl class | |invoking getUserById method ||User ");
		return u;

	}

	@Override
	public User updateUserService(int id, User user) throws ResourceNotFoundException {
		User opUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
		opUser.setId(id);
		opUser.setName(user.getName());
		opUser.setContact(user.getName());
		opUser.setEmail(user.getEmail());
		opUser.setPassword(user.getPassword());
		opUser.setImage_url(user.getImage_url());
		
		LOGGER.debug("updateUserService method invoked |opUser : " + opUser);
		final User u = userRepository.save(user);
		LOGGER.debug("updateUserService method invoked |Updated User : " + u);
		return u;
	}

	@Override
	public void deleteUserService(int id) throws ResourceNotFoundException {
		User user = userRepository.findById(id).
				orElseThrow(()->new ResourceNotFoundException("Employee not found for this id :: " + id));
			userRepository.delete(user);
	}

	@Override
	public Page<User> userPageCount(Pageable pageable) {
		// TODO Auto-generated method stub
		return userRepository.findAll(pageable);
	}

//	getTopUserForDashboard
	@Override
	public List<User> getAllUserByIdDesc() {
		return customRepo.findAllUserByIdDesc();
	}
	

}
