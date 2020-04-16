package com.diploma.DAO;

import com.diploma.Entities.UsersEntity;
import org.hibernate.Transaction;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UsersEntityImpl extends EntityBase implements UserDao {

    public void addUser(UsersEntity user) {
        Transaction tr = session.beginTransaction();
        session.save(user);
        tr.commit();
//        logger.info("User successfully added. User details: " + user);
//        session.close();
    }

    public void updateUser(UsersEntity user) {
        Transaction tr = session.beginTransaction();
        session.update(user);
        tr.commit();
//        logger.info("User successfully updated. User details: " + user);
//        session.close();
    }

    public boolean removeUser(int uid) {
        Transaction tr = session.beginTransaction();
        UsersEntity user = session.load(UsersEntity.class, new Integer(uid));

        if (user != null) {
            session.delete(user);
            tr.commit();
            return true;
        }

        return false;
    }

    public UsersEntity getUserByUid(int uid) {
        UsersEntity user = session.load(UsersEntity.class, new Integer(uid));
        return user;
    }

    public UsersEntity getUserByLogin(String login) {
        UsersEntity user = session.load(UsersEntity.class, login);
        return user;
    }

    public List<UsersEntity> listUsers() {
        List<UsersEntity> usersList = session.createQuery("from UsersEntity").list();
        return usersList;
    }
}
