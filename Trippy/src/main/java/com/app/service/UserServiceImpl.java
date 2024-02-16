package com.app.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.ApiResponse;
import com.app.dto.UserDTO;
import com.app.dto.UserDTOSignedIN;
import com.app.entities.User;
import com.app.repository.UserInterface;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserInterface ur;
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public ApiResponse delete(long id) {
		User ogUser=ur.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found"));
		
		ur.delete(ogUser);
		if(ur.existsById(id)) {
			return new  ApiResponse("Not Deleted");
		}
		else {
			return new  ApiResponse(" Deleted");
		}
	
	}

	@Override
	public ApiResponse Create(UserDTO user) {
		if(ur.existsByEmail(user.getEmail())) {
			return new  ApiResponse("Email Already exists");
		}else {
			User u=ur.save(mapper.map(user,User.class));
			if(ur.existsById(u.getUserID())) {
				return new  ApiResponse("Created");
			}
			else {
				return new  ApiResponse("Not Created");
		}}
		
	}

	@Override
	public UserDTO update(UserDTO user, long id) {
		User ogUser=ur.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found"));
		mapper.map(user,ogUser);
		System.out.println(ogUser.getUserID());
		 ogUser.setUserName(user.getUserName());
		    ogUser.setEmail(user.getEmail());
		    ogUser.setPhoneNo(user.getPhoneNo());
		    ogUser.setPassword(user.getPassword());
		    User saved=ur.save(ogUser);
		return mapper.map(saved, UserDTO.class);
	}

	@Override
	public UserDTO get(long id) {
		User ogUser=ur.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found"));
		return mapper.map(ogUser, UserDTO.class);
	}

	@Override
	public UserDTOSignedIN SignIn(String email, String password) {
		 User u = ur.findByEmail(email);
	        if (u != null && passwordEncoder.matches(password, u.getPassword())) {
	            return mapper.map(u, UserDTOSignedIN.class);
	        }
	        
		return null;
	}


}
