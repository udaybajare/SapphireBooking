package com.sapphire.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sapphire.entity.UserDetails;

@Repository
public class RegistrationDao {

	public RegistrationDao() {
	}

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void saveNewUserDetails(UserDetails userDetails) {
		Session session = sessionFactory.getCurrentSession();

		long serialNumberCustomer = getMaxSerialNo() + 1;
		userDetails.setSerialNumberCustomer(serialNumberCustomer);

		try {
			session.save(userDetails);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Transactional
	public long getMaxSerialNo() {
		Session session = sessionFactory.getCurrentSession();

		String sql = "select max(serialNumberCustomer) FROM UserDetails ";

		Query qry = session.createQuery(sql);

		Object obj = qry.uniqueResult();
		long maxSrNo = (obj != null) ? (long) obj : 0;

		return maxSrNo;
	}

	@Transactional
	public String getRelatedOrganiztion(String userName) {
		Session session = sessionFactory.getCurrentSession();

		String selectHql = "select us.orgName FROM UserDetails us where us.userName=:userName";

		Query query = session.createQuery(selectHql);
		query.setParameter("userName", userName);

		List loginList = query.getResultList();

		return (String) loginList.get(0);

	}

}
