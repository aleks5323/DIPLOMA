package com.diploma.DAO;

import com.diploma.Entities.ConversationsEntity;
import com.diploma.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ConversationsEntityImpl implements ConversationsDAO {

    private static ConversationsEntityImpl INSTANCE;

    private ConversationsEntityImpl() {}

    public static ConversationsEntityImpl getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ConversationsEntityImpl();

        return INSTANCE;
    }

    private Session session = HibernateUtil.getSession();

    @Override
    public void newConversation(ConversationsEntity convo) {
        Transaction tr = session.beginTransaction();
        session.save(convo);
        tr.commit();
    }

    @Override
    public void updateConversation(ConversationsEntity convo) {
        Transaction tr = session.beginTransaction();
        session.update(convo);
        tr.commit();
    }

    @Override
    public void removeConversation(int cid) {
        ConversationsEntity convo = session.load(ConversationsEntity.class, new Integer(cid));

        if (convo != null)
            session.delete(convo);
    }

    @Override
    public ConversationsEntity getConvoById(int cid) {
        ConversationsEntity convo = session.load(ConversationsEntity.class, cid);
        return convo;
    }

    @Override
    public ConversationsEntity getConvoByUser(String login) {
        ConversationsEntity convo = session.load(ConversationsEntity.class, login);
        return convo;
    }

    @Override
    public List<ConversationsEntity> listConvos() {
        List<ConversationsEntity> convosList = session.createQuery("from ConversationsEntity").list();
        return convosList;
    }
}
