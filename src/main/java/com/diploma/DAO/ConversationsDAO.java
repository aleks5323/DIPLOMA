package com.diploma.DAO;

import com.diploma.Entities.ConversationsEntity;

import java.util.List;
import java.util.UUID;

public interface ConversationsDAO {

    boolean newConversation(ConversationsEntity convo);
    boolean updateConversation(ConversationsEntity convo);
    boolean removeConversation(UUID cid);
    ConversationsEntity getConvoById(UUID cid);
    ConversationsEntity getConvoByUser(String login);
    List<ConversationsEntity> listConvos();
    int getConvoCount();

}
