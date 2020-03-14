package com.sapphire.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sapphire.entity.SessionEntry;

@Repository
public class SessionDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public boolean isSessionIdPresent(SessionEntry sessionEntry) {

		Session session = sessionFactory.getCurrentSession();

		String selectHql = "select count(SE.sessionId) FROM SessionEntry SE where userName=:userName";

		Query query = session.createQuery(selectHql);

		query.setParameter("userName", sessionEntry.getUserName());

		List sessionList = query.list();

		return (long) sessionList.get(0) > 0;
	}

	@Transactional
	public boolean saveSession(SessionEntry sessionEntry) {
		boolean sessionSaved = true;
		try {
			Session session = sessionFactory.getCurrentSession();
			session.persist(sessionEntry);
		} catch (Exception ex) {
			sessionSaved = false;
			ex.printStackTrace();
		}
		return sessionSaved;
	}

	@Transactional
	public boolean deleteSession(String sessionId) {
		boolean sessiondelete = true;
		try {
			Session session = sessionFactory.getCurrentSession();

			String sql = "delete from SessionEntry where sessionId=:sessionId";

			Query query = session.createQuery(sql);
			query.setParameter("sessionId", sessionId);

			query.executeUpdate();

		} catch (Exception ex) {
			sessiondelete = false;
			ex.printStackTrace();
		}

		return sessiondelete;
	}

}
