package com.diploma.DAO;

import com.diploma.Entities.ConversationsEntity;

import java.util.List;

public interface ConversationsDAO {

    public void newConversation(ConversationsEntity convo);
    public void updateConversation(ConversationsEntity convo);
    public void removeConversation(int cid);
    public ConversationsEntity getConvoById(int cid);
    public ConversationsEntity getConvoByUser(String login);
    public List<ConversationsEntity> listConvos();

}
