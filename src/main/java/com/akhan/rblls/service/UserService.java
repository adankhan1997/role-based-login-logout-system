package com.akhan.rblls.service;

import com.akhan.rblls.entity.User;

public interface UserService {

    void saveUser(User user);
    User findByEmail(String email);
}
