package com.ben.service;

public interface UserService {

    void sendEmail();

    Boolean isExists(String userName,String password);
}
