package ChatApplicationProject.Controller;

import ChatApplicationProject.Models.Message;
import ChatApplicationProject.requestDto.MessageRequest;
import ChatApplicationProject.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/sendmessage")
    public ResponseEntity<Message> sendMessage(@RequestBody MessageRequest messageRequest,@RequestHeader("Authorization") String jwtToken){

        return new ResponseEntity<>(messageService.sendMessage(messageRequest), HttpStatus.CREATED);
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<Message>> sendMessage(@PathVariable Integer chatId, @RequestHeader("Authorization") String jwtToken){
        return new ResponseEntity<>(messageService.getAllMessageByChatId(chatId),HttpStatus.OK);
    }
}
