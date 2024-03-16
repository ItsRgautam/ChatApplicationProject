package ChatApplicationProject.services;

import ChatApplicationProject.Models.User;
import ChatApplicationProject.requestDto.UserRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

    public User register(UserRequest userRequest) throws Exception;

    public User findById(Integer userid2);

    public User findByUsername(String Username);

    public User getUser();
}
