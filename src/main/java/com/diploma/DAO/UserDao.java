package com.diploma.DAO;

import com.diploma.Entities.UsersEntity;

import java.util.List;

public interface UserDao {

    void addUser(UsersEntity user);
    void updateUser(UsersEntity user);
    boolean removeUser(int uid);
    UsersEntity getUserByUid(int uid);
    UsersEntity getUserByLogin(String login);
    List<UsersEntity> listUsers();

}
