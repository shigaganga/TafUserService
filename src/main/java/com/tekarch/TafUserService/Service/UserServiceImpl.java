package com.tekarch.TafUserService.Service;

import com.tekarch.TafUserService.Models.UserDTO;
import com.tekarch.TafUserService.Service.Interface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RestTemplate restTemplate;
   // private final String DATASOURCE_URL = "http://localhost:8080/users"; // URL to TafDatastoreService
   @Value("${datasource.url}")
   private String DATASOURCE_URL;
    @Override
    // This method creates a new user by sending a POST request to the TafDatastoreService with the provided userDTO.
    public UserDTO createUser(UserDTO userDTO) {
        ResponseEntity<UserDTO> response=restTemplate.postForEntity(DATASOURCE_URL,userDTO, UserDTO.class);
        //Sends a POST request to the DATASOURCE_URL, including the userDTO in the request body. It expects a response of type UserDTO
        return response.getBody();
        //After receiving the response, it extracts and returns the body of the response, which is the created UserDTO.
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> users=restTemplate.exchange(DATASOURCE_URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<UserDTO>>() {
        }).getBody();
        return users;
        //.exchange is more flexible http request& sends a GET request to the DATASOURCE_URL and expects a list of UserDTO objects in response. exchange()
        // allows you to specify the request method, request entity, and response type.
        //exchange() allows you to specify the request method, request entity, and response type.
       //parameterizedtypereference This is used to specify the generic type (List<UserDTO>) of the response
        // because Java’s type system doesn't retain generic types at runtime (type erasure).
        //getBody(): Extracts the list of users from the response.
    }

    @Override
    public UserDTO getUserById(Long id) {
        String url=DATASOURCE_URL+"/"+id;
        return restTemplate.getForObject(url, UserDTO.class);
       // Sends a GET request to the constructed URL and expects a response containing a UserDTO.
    }

    @Override
    public UserDTO updateUserById(Long id, UserDTO userDTO) {
        //System.out.println("From "+userDTO);
        String url=DATASOURCE_URL+"/"+id;
        restTemplate.put(url,userDTO);
        //Sends a PUT request to the URL and includes the updated userDTO in the body of the request.
        return userDTO;
        //After updating, it returns the updated userDTO.
    }

    @Override
    public void deleteUser(Long id) {
String url=DATASOURCE_URL+"/"+id;
restTemplate.delete(url);
//Sends a DELETE request to the URL, which deletes the user
    }
}
