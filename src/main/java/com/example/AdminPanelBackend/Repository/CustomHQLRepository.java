package com.example.AdminPanelBackend.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.AdminPanelBackend.Entity.User;

public interface CustomHQLRepository {

	List<User> findAllUserBySortingOrder();
	
	List<User> findAllUserByIdDesc();
}
