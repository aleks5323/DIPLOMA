package com.diploma.DAO;

import com.diploma.Entities.ConversationsEntity;

import java.util.List;

public interface ConversationsDAO {

    boolean newConversation(ConversationsEntity convo);
    boolean updateConversation(ConversationsEntity convo);
    boolean removeConversation(int cid);
    ConversationsEntity getConvoById(int cid);
    ConversationsEntity getConvoByUser(String login);
    List<ConversationsEntity> listConvos();
    int getConvoCount();

}
