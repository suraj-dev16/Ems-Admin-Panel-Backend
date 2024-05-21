package com.example.AdminPanelBackend.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.AdminPanelBackend.Entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class CustomHQLRepositoryImpl implements CustomHQLRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<User> findAllUserBySortingOrder() {
		
		String hql_query = "SELECT u FROM User u";
		Query query =  entityManager.createQuery(hql_query,User.class);
		List<User> users = query.getResultList();
		
		return users;
	}

	@Override
	public List<User> findAllUserByIdDesc() {
		// TODO Auto-generated method stub
		String hql_query = "SELECT u FROM User u ORDER BY u.id DESC";
		Query query = entityManager.createQuery(hql_query,User.class);
		//TypedQuery
		
		List<User> users = query.getResultList();
		return users;
	}
	

}
