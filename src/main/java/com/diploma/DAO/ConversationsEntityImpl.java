package com.diploma.DAO;

import com.diploma.Entities.ConversationsEntity;
import org.hibernate.Transaction;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ConversationsEntityImpl extends EntityBase implements ConversationsDAO {

    @Override
    public boolean newConversation(ConversationsEntity convo) {
        Transaction tr = session.beginTransaction();

        try {
            session.save(convo);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            return false;
        }
    }

    @Override
    public boolean updateConversation(ConversationsEntity convo) {
        Transaction tr = session.beginTransaction();

        try {
            session.update(convo);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            return false;
        }
    }

    @Override
    public boolean removeConversation(int cid) {
        ConversationsEntity convo = session.load(ConversationsEntity.class, new Integer(cid));
        Transaction tr = session.beginTransaction();

        try {
            session.delete(convo);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            return false;
        }
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

    @Override
    public int getConvoCount() {
        return listConvos().size();
    }
}
