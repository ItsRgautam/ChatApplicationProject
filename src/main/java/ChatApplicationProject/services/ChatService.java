package ChatApplicationProject.services;

import ChatApplicationProject.Models.Chat;
import ChatApplicationProject.Models.User;

import java.util.List;

public interface ChatService {

    public Chat createChat(String username) ;

    public Chat findChatByName(String name) ;

    public List<Chat> findAllChatByUserid(Integer userid);

    public Chat createGroup(String name, List<User> usersList) ;

    public Chat addMemberToGroup(Integer userid, Integer chatid) ;

    public Chat findChatById(Integer chatId);
}
