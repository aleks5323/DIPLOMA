package com.diploma.DAO;

import com.diploma.Utils.HibernateUtil;
import org.hibernate.Session;

public class EntityBase {
    Session session = HibernateUtil.getSession();
}
