package ChatApplicationProject.services;

import ChatApplicationProject.Models.Chat;
import ChatApplicationProject.Models.User;
import ChatApplicationProject.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserService userService;

    @Override
    public Chat createChat(String username)  {
        User user=userService.getUser();
        User user2=userService.findByUsername(username);

        Chat ChatExists = chatRepository.findSingleChatByUserIds(user,user2);
        if(ChatExists==null){
            Set<User> userSet=new HashSet<>();
            userSet.add(user);
            userSet.add(user2);
            Chat chat=new Chat();
            chat.setCreatedby(user);
            chat.setIsgroup(false);
            chat.setUsers(userSet);
            Chat newChat=  chatRepository.save(chat);


            if(user.getChatIdList()==null){
                List<Integer> chatIdList=new ArrayList<>();
                chatIdList.add(newChat.getId());
                user.setChatIdList(chatIdList);
            }
            else
                user.getChatIdList().add(newChat.getId());

            if(user2.getChatIdList()==null){
                List<Integer> chatIdList=new ArrayList<>();
                chatIdList.add(newChat.getId());
                user2.setChatIdList(chatIdList);
            }
            else
                user2.getChatIdList().add(newChat.getId());

            return newChat;
         }
        return ChatExists;
    }

    public Chat findChatById(Integer chatId){
       Optional<Chat> chat= chatRepository.findById(chatId);
       return chat.get();
    }

    @Override
    public Chat findChatByName(String name) {
        return null;
    }

    @Override
    public List<Chat> findAllChatByUserid(Integer userid)  {
        try {
            User user=userService.findById(userid);
            if(user==null)
                throw new Exception("user not exist");

            List<Integer> chatIds=user.getChatIdList();
            return chatRepository.findAllChatByUserId(chatIds);
        }
        catch (Exception e){
             return null;
        }
    }

    @Override
    public Chat createGroup(String name, List<User> usersList) {
        return null;
    }

    @Override
    public Chat addMemberToGroup(Integer userid, Integer chatid)  {
        return null;
    }
}
