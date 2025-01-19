package com.tekarch.TafUserService.Service.Interface;

import com.tekarch.TafUserService.Models.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO updateUserById(Long id,UserDTO userDTO);
    void deleteUser(Long id);
}
