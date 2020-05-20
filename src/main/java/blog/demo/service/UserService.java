package blog.demo.service;

import blog.demo.entity.User;

public interface UserService {
    User checkUser(String username, String password);

}
