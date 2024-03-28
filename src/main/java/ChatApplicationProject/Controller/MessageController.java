package ChatApplicationProject.Controller;

import ChatApplicationProject.Models.Message;
import ChatApplicationProject.Models.User;
import ChatApplicationProject.requestDto.MessageReq;
import ChatApplicationProject.requestDto.MessageRequest;
import ChatApplicationProject.services.ChatService;
import ChatApplicationProject.services.MessageService;
import ChatApplicationProject.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatService chatService;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/application")
    @SendTo("/all/messages")
    public MessageReq send( MessageReq message) throws Exception {
        return message;
    }

    @MessageMapping("/private")
    @ResponseBody
    public Message sendToSpecificUser(@Payload MessageRequest messageRequest){
        Message message=messageService.sendMessage(messageRequest);
        Set<User> users=chatService.findChatById(message.getChat().getId()).getUsers();
        User[] userArray=new User[users.size()];
        userArray=users.toArray(userArray);
        for(User u:userArray ){
            simpMessagingTemplate.convertAndSendToUser(u.getUsername(),"/specific",message);
        }
        return message;
    }

    @PostMapping("/sendmessage")
    @ResponseBody
    public ResponseEntity<Message> sendMessage(@RequestBody MessageRequest messageRequest,@RequestHeader("Authorization") String jwtToken){

        return new ResponseEntity<>(messageService.sendMessage(messageRequest), HttpStatus.CREATED);
    }

    @GetMapping("/chat/messages/{chatId}")
    @ResponseBody
    public ResponseEntity<List<Message>> sendMessage(@PathVariable Integer chatId, @RequestHeader("Authorization") String jwtToken){
        return new ResponseEntity<>(messageService.getAllMessageByChatId(chatId),HttpStatus.OK);
    }

}
