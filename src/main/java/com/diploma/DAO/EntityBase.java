package com.diploma.DAO;

import com.diploma.Utils.HibernateUtil;
import org.hibernate.Session;

import javax.inject.Inject;

public class EntityBase {
    Session session = HibernateUtil.getSession();
}
