package ChatApplicationProject.Controller;

import ChatApplicationProject.Models.Chat;
import ChatApplicationProject.services.ChatService;
import ChatApplicationProject.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/createchat")
    public ResponseEntity<Chat> createChat(@RequestParam String username, @RequestHeader("Authorization") String jwtToken){
        return new ResponseEntity<>(chatService.createChat(username), HttpStatus.CREATED);
    }
}
