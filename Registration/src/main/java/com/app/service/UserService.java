package com.app.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.app.model.User;
import com.app.repo.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository cr;
	
	public List<User> findAll(){
		return cr.findAll();
	}
	
	public Optional<User> findById(Long passangerId){
		return cr.findById(passangerId);
	}
	
	public User save(User passanger){
		return cr.save(passanger);
	}
	
	public void delete(User passanger) {
		cr.delete(passanger);
	}
}
