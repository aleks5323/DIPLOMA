package com.diploma.DAO;

import com.diploma.Entities.ConversationsEntity;
import com.diploma.Entities.UsersEntity;
import org.hibernate.Transaction;

import javax.enterprise.context.ApplicationScoped;
import javax.xml.registry.infomodel.User;
import java.util.List;
import java.util.UUID;

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
    public boolean removeConversation(UUID cid) {
        ConversationsEntity convo = session.load(ConversationsEntity.class, cid);
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
    public ConversationsEntity getConvoById(UUID cid) {
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
        for (ConversationsEntity t : convosList) {
            UsersEntity ut = (UsersEntity)session.createQuery("from UsersEntity where uid = " + t.getPerformedBy()).getSingleResult();
            t.setAuthorName(ut.getUname());
        }
        return convosList;
    }

    @Override
    public int getConvoCount() {
        return listConvos().size();
    }
}
