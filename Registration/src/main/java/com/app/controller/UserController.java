package com.app.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.ResourceNotFoundException;
import com.app.model.User;
import com.app.repo.UserRepository;
import com.app.service.UserService;

@RestController
@RequestMapping("A")
public class UserController {

	 @Autowired
	    private UserService userService;

	    @GetMapping("/passanger")
	    public List<User> getAllCandidates() {
	        return userService.findAll();
	    }

	    @GetMapping("/passanger/{id}")
	    public ResponseEntity<User> getPassangerById(@PathVariable(value = "id") Long passangerId)
	        throws ResourceNotFoundException {
	    	User passanger = userService.findById(passangerId)
	          .orElseThrow(() -> new ResourceNotFoundException("passanger not found for this id :: " + passangerId));
	        return ResponseEntity.ok().body(passanger);
	    }
	    
	    @PostMapping("/passanger")
	    public User createCandidate(@RequestBody User passanger) {
	        return userService.save(passanger);
	    }

	    @PutMapping("/passanger/{id}")
	    public ResponseEntity<User> updateCandidate(@PathVariable(value = "id") Long passangerId,
	         @RequestBody User passangerDetails) throws ResourceNotFoundException {
	    	User passanger = userService.findById(passangerId)
	        .orElseThrow(() -> new ResourceNotFoundException("passanger not found for this id :: " +passangerId));
	        
	    	passanger.setId(passangerDetails.getId());
	    	passanger.setFname(passangerDetails.getFname());
	    	passanger.setLname(passangerDetails.getLname());
	    	passanger.setEmail(passangerDetails.getEmail());
	    	passanger.setPassword(passangerDetails.getPassword());
	    	passanger.setState(passangerDetails.getState());
	    	passanger.setCity(passangerDetails.getCity());
	    	passanger.setMobile(passangerDetails.getMobile());
	    	passanger.setDob(passangerDetails.getDob());
	   
	    
	        final User updatePassanger = userService.save(passanger);
	        return ResponseEntity.ok(updatePassanger);
	    }

	    @DeleteMapping("/passanger/{id}")
	    public Map<String, Boolean> deletepassanger(@PathVariable(value = "id") Long passangerId)
	         throws ResourceNotFoundException {
	    	User passanger = userService.findById(passangerId)
	       .orElseThrow(() -> new ResourceNotFoundException("passanger not found for this id :: " + passangerId));

	    	userService.delete(passanger);
	        Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	    }
	
}

