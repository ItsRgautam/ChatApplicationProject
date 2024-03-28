package ChatApplicationProject.Controller;

import ChatApplicationProject.Models.Chat;
import ChatApplicationProject.requestDto.GroupChatRequest;
import ChatApplicationProject.services.ChatService;
import ChatApplicationProject.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {

    @Autowired
    private ChatService chatService;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("chat/createchat")
    public ResponseEntity<Chat> createChat(@RequestParam("username") String username, @RequestHeader("Authorization") String token){
        System.out.println("jwt token received-------------------"+token);
        return new ResponseEntity<>(chatService.createChat(username), HttpStatus.CREATED);
    }

    @GetMapping("/chat/find")
    public ResponseEntity<Chat> findChatById(@RequestParam Integer chatId, @RequestHeader("Authorization") String token){
        return new ResponseEntity<>(chatService.findChatById(chatId),HttpStatus.CREATED);
    }
    @PostMapping(value = "chat/creategroup", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<Chat> createGroup(@RequestBody GroupChatRequest groupChatRequest, @RequestHeader("Authorization") String token){


        return new ResponseEntity<>(chatService.createGroup(groupChatRequest),HttpStatus.CREATED);
    }

    @GetMapping("/my/chats")
    public ResponseEntity<List<Chat>> myChats( @RequestParam Integer userId,@RequestHeader("Authorization") String token){
        return new ResponseEntity<>(chatService.findAllChatByUserid(userId),HttpStatus.OK);
    }
}
