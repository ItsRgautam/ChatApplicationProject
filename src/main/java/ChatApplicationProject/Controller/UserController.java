package ChatApplicationProject.Controller;

import ChatApplicationProject.Models.User;
import ChatApplicationProject.requestDto.UserRequest;
import ChatApplicationProject.services.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;


    @GetMapping("/search-user")
    public ResponseEntity<List<User>> searchUser(@RequestParam("search") String search,@RequestHeader("Authorization") String token ){
        System.out.println("jwt token received-------------------"+token);
        return  new ResponseEntity<>(userServiceImpl.searchUser(search),HttpStatus.OK);
    }
    @GetMapping("/fetchuser")
    public ResponseEntity<User> fetchUser(@RequestParam("userId") Integer userId,@RequestHeader("Authorization") String token ){
        System.out.println("jwt token received-------------------"+token);
        return  new ResponseEntity<>(userServiceImpl.findById(userId),HttpStatus.OK);
    }

    @GetMapping("/fetchallusers")
    public ResponseEntity<List<User>> fetchAllUsers(@RequestHeader("Authorization") String token ){
        System.out.println("jwt token received-------------------"+token);
        return  new ResponseEntity<>(userServiceImpl.fetchAllUsers(),HttpStatus.OK);
    }


}
