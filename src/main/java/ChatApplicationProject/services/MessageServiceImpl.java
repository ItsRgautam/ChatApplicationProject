package ChatApplicationProject.services;

import ChatApplicationProject.Models.Chat;
import ChatApplicationProject.Models.Message;
import ChatApplicationProject.Models.User;
import ChatApplicationProject.repository.MessageRepository;
import ChatApplicationProject.requestDto.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private UserService userService;
    @Autowired
    private ChatService chatService;
    @Autowired
    private MessageRepository messageRepository;
    @Override
    public Message sendMessage(MessageRequest messageRequest) {

        User user=userService.getUser();
        Chat chat= chatService.findChatById(messageRequest.getChatId());
        if(user!= null && chat!= null){
        Message newMessage= new Message();
        newMessage.setChat(chat);
        newMessage.setUser(user);
        newMessage.setContent(messageRequest.getContent());

        return messageRepository.save(newMessage);
        }

     return null;
    }

    @Override
    public List<Message> getAllMessageByChatId(Integer chatId) {
       Chat chat= chatService.findChatById(chatId);

       return chat.getMessages();
    }
}
