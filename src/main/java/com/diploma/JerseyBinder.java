package com.diploma;

import com.diploma.DAO.ConversationsEntityImpl;
import com.diploma.DAO.UsersEntityImpl;
import com.diploma.Entities.ConversationsEntity;
import com.diploma.Utils.HibernateUtil;
import org.glassfish.jersey.internal.inject.AbstractBinder;

public class JerseyBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(new HibernateUtil()).to(HibernateUtil.class);
        bind(new UsersEntityImpl()).to(UsersEntityImpl.class);
        bind(new ConversationsEntityImpl()).to(ConversationsEntityImpl.class);
    }
}
