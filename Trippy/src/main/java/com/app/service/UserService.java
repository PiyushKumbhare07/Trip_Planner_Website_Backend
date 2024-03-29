package com.app.service;

import com.app.dto.ApiResponse;
import com.app.dto.UserDTO;
import com.app.dto.UserDTOSignedIN;
import com.app.dto.UserDTOupdation;
import com.app.entities.User;

public interface UserService {
	ApiResponse delete(long id);
ApiResponse Create(UserDTO user);
UserDTOupdation update(UserDTOupdation user,long id);
UserDTO get(long id);
UserDTOSignedIN SignIn(String email,String password);
boolean updatePassword(long userId, String newPassword);
}
