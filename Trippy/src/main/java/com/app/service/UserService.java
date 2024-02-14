package com.app.service;

import com.app.dto.ApiResponse;
import com.app.dto.UserDTO;
import com.app.entities.User;

public interface UserService {
	ApiResponse delete(long id);
ApiResponse Create(UserDTO user);
UserDTO update(UserDTO user,long id);
UserDTO get(long id);
}
