package ChatApplicationProject.services;

import ChatApplicationProject.Models.Chat;
import ChatApplicationProject.Models.User;
import ChatApplicationProject.repository.ChatRepository;
import ChatApplicationProject.requestDto.GroupChatRequest;
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

        Chat chatExists = chatRepository.findSingleChatByUserIds(user,user2);
        if(chatExists==null){
            Chat chat=new Chat();
            Set<User> userSet= chat.getUsers();
            userSet.add(user);
            userSet.add(user2);
            chat.setCreatedby(user.getName()+"("+user.getUsername()+")");
            chat.setIsgroup(false);
            chat.setUsers(userSet);

            Chat newChat=chatRepository.save(chat);

            user.getChatIdList().add(newChat.getId());
            user2.getChatIdList().add(newChat.getId());

            userService.save(user);
            userService.save(user2);

            return newChat;
         }
        return chatExists;
    }

    public Chat findChatById(Integer chatId){
    	
       Optional<Chat> chat= chatRepository.findById(chatId);
     //  System.out.println("chat0"+chat);
       return chat.get();
    }
    public Set<User> findUserByChatId(Integer chatId){

        Optional<Chat> chat= chatRepository.findById(chatId);
        //  System.out.println("chat0"+chat);
        return chat.get().getUsers();
    }

    @Override
    public Chat findChatByName(String name) {
        return null;
    }

    @Override
    public List<Chat> findAllChatByUserid(Integer userid)  {

        // finding all chats of a particular user
        try {
            User user=userService.findById(userid);
            if(user==null)
                throw new Exception("user not exist");
            List<Chat> chats=new ArrayList<>();

            Set<Integer> chatlist=user.getChatIdList();
            System.out.println("chatlist of user"+chatlist.toString());
            Iterator<Integer> iterator=chatlist.iterator();

            while(iterator.hasNext()){
                Chat chat=this.findChatById(iterator.next());
                chats.add(chat);
                System.out.println("chat added---->>>"+chat.getId());
            }
            return chats;
        }
        catch (Exception e){
             return null;
        }
    }

    @Override
    public Chat createGroup(GroupChatRequest groupChatRequest) {
       Chat chat=new Chat();

      chat.setChatname(groupChatRequest.getGroupChatName());
        System.out.println("creating groupchat name:"+groupChatRequest.getGroupChatName());
       Chat newGroupChat=chatRepository.save(chat);

       Set<User> listUser= newGroupChat.getUsers();


       List<Integer> userid= groupChatRequest.getUserIds();
        System.out.println("user list"+groupChatRequest.getUserIds().toString());

        Iterator<Integer> iterator=userid.iterator();
        while (iterator.hasNext()){
           Integer i= iterator.next();
           User user=userService.findById(i);
           listUser.add(user);
            System.out.println("user adder---->>>>"+user.getUsername());
            Set<Integer> chatlist=user.getChatIdList();
            chatlist.add(newGroupChat.getId());
            userService.save(user);

        }
        listUser.add(userService.getUser());
        newGroupChat.setUsers(listUser);
        newGroupChat.setIsgroup(true);
        newGroupChat.setCreatedby(userService.getUser().getName()+"("+userService.getUser().getUsername()+")");

        Set<Integer> listAdmin=newGroupChat.getAdmins();

        listAdmin.add(userService.getUser().getId());

        newGroupChat.setAdmins(listAdmin);
      User modifiedUser= userService.getUser();
      modifiedUser.getChatIdList().add(newGroupChat.getId());
      userService.save(modifiedUser);


        System.out.println("group chat created"+newGroupChat.toString());
      return chatRepository.save(newGroupChat);
    }

    @Override
    public Chat addMemberToGroup(List<Integer> userid, Integer chatid)  {

        Integer loggedUser=userService.getUser().getId();
        Chat chat=this.findChatById(chatid);
        Set<Integer> admins=chat.getAdmins();

         if(admins.contains(loggedUser)){
             Set<User> users=chat.getUsers();
           //add users to the chat

            return chat;
        }
        else {
            throw new RuntimeException("you are not a admin");
        }
    }
}
