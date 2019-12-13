package com.sapphire.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sapphire.entity.LoginDetails;

@Repository
public class LoginDetailsDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public String getPasswordToValidate(LoginDetails loginDetails) {
		Session session = sessionFactory.getCurrentSession();

		String selectHql = "select logd.password FROM LoginDetails logd where logd.userName=:userName";

		Query query = session.createQuery(selectHql);
		query.setParameter("userName", loginDetails.getUserName());
		
		List loginList = query.getResultList();

		return (String)loginList.get(0);
	}

}
