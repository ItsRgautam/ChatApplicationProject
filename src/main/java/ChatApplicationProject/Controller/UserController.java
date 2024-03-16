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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody UserRequest userRequest) throws Exception{

        return new ResponseEntity<>(userServiceImpl.register(userRequest), HttpStatus.CREATED);
    }

}
