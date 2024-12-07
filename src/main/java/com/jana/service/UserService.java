package com.jana.service;

import com.jana.model.User;

public interface UserService {
    //To find user by jwt token

    public User findUserByJwtToken(String jwt) throws Exception;
    public User findUserByEmail(String email) throws Exception;


}
