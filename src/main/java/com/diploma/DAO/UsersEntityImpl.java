package com.diploma.DAO;

import com.diploma.Entities.UsersEntity;
import com.diploma.Utils.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UsersEntityImpl implements UserDao {

    private static UsersEntityImpl INSTANCE;

    private UsersEntityImpl() {
    }

    public static UsersEntityImpl getInstance() {
        if (INSTANCE == null)
            INSTANCE = new UsersEntityImpl();

        return INSTANCE;
    }

//    private static final Logger logger = LoggerFactory.getLogger(UsersEntityImpl.class);
    private Session session = HibernateUtil.getSession();

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

    public void removeUser(int uid) {
        Transaction tr = session.beginTransaction();
        UsersEntity user = (UsersEntity) session.load(UsersEntity.class, new Integer(uid));

        if (user != null) {
            session.delete(user);
//            logger.info("User successfully deleted. User details: " + user);
        }

        tr.commit();
//        session.close();
    }

    public UsersEntity getUserByUid(int uid) {
        UsersEntity user = (UsersEntity) session.load(UsersEntity.class, new Integer(uid));

        return user;
    }

    public UsersEntity getUserByLogin(String login) {
        UsersEntity user = (UsersEntity) session.load(UsersEntity.class, new String(login));

        return user;
    }

    public List<UsersEntity> listUsers() {
        List<UsersEntity> usersList = session.createQuery("from UsersEntity").list();

//        for(UsersEntity user : usersList) {
//            logger.info("User in list: " + user);
//        }

        return usersList;
    }
}
