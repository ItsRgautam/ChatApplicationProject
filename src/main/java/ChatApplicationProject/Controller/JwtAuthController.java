package ChatApplicationProject.Controller;

import ChatApplicationProject.Models.JwtRequest;
import ChatApplicationProject.Models.JwtResponse;
import ChatApplicationProject.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthController {

    @Autowired
    private JwtService jwtService;
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest){

        return new ResponseEntity<>(jwtService.login(jwtRequest), HttpStatus.OK);
    }
}
