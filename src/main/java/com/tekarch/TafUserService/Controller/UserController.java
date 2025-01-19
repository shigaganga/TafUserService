package com.tekarch.TafUserService.Controller;

import com.tekarch.TafUserService.Models.UserDTO;
import com.tekarch.TafUserService.Service.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);
    @Autowired
    private UserServiceImpl userServiceImpl;
    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO){
        return userServiceImpl.createUser(userDTO);
    }
    @GetMapping
    public List< UserDTO> getAllUsers(){
        return userServiceImpl.getAllUsers();
    }
    @ GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id){
        return userServiceImpl.getUserById(id);
    }
    @PutMapping("/{id}")
   public UserDTO updateUser(@PathVariable Long id,@RequestBody UserDTO userDTO){
   return userServiceImpl.updateUserById(id,userDTO);
}
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        System.out.println("id from user"+id);
         userServiceImpl.deleteUser(id);

}
    // Exception Handling
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleExceptions(Exception e) {
        logger.error("Exception occurred: {}", e.getMessage());
        return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
