package com.diploma.DAO;

import com.diploma.Entities.UsersEntity;

import java.util.List;

public interface UserDao {

    public void addUser(UsersEntity user);
    public void updateUser(UsersEntity user);
    public void removeUser(int uid);
    public UsersEntity getUserByUid(int uid);
    public UsersEntity getUserByLogin(String login);
    public List<UsersEntity> listUsers();

}
