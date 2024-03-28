package ChatApplicationProject.services;

import ChatApplicationProject.Models.Chat;
import ChatApplicationProject.requestDto.GroupChatRequest;

import java.util.List;

public interface ChatService {

    public Chat createChat(String username) ;

    public Chat findChatByName(String name) ;

    public List<Chat> findAllChatByUserid(Integer userId);

    public Chat createGroup(GroupChatRequest groupChatRequest) ;


    public Chat addMemberToGroup(List<Integer> userid, Integer chatid) ;

    public Chat findChatById(Integer chatId);
}
