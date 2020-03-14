package com.sapphire.dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
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
	public boolean saveNewUserDetails(UserDetails userDetails, boolean isSessionPresent, boolean userPresent) {
		boolean result = true;

		try {
			Session session = sessionFactory.getCurrentSession();

			if (userPresent && isSessionPresent)
			{
				userDetails.setStatus("Accept");
				session.update(userDetails);
			}
			else
			{
				session.save(userDetails);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		}

		return result;
	}

	@Transactional
	public int getMaxSerialNo() {
		Session session = sessionFactory.getCurrentSession();

		String sql = "select max(serialNumberCustomer) FROM UserDetails ";

		Query qry = session.createQuery(sql);

		Object obj = qry.uniqueResult();
		int maxSrNo = (obj != null) ? (int) obj : 0;

		return maxSrNo;
	}

	@Transactional
	public String getRelatedOrganiztion(String userName) {
		Session session = sessionFactory.getCurrentSession();

		String selectHql = "select us.orgName FROM UserDetails us  where us.userName=:userName";

		Query query = session.createQuery(selectHql);
		query.setParameter("userName", userName);

		List loginList = query.getResultList();

		return (String) loginList.get(0);

	}

	@Transactional
	public UserDetails getUserCurrentDetails(String userName) {
		Session session = sessionFactory.getCurrentSession();

		String selectHql = "FROM UserDetails us  where us.userName=:userName";

		Query query = session.createQuery(selectHql);
		query.setParameter("userName", userName);

		UserDetails userDetails = (UserDetails) query.uniqueResult();

		return userDetails;

	}

	@Transactional
	public ArrayList<UserDetails> getUserDetails() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "FROM UserDetails WHERE status = 'Pending'";
		Query query = session.createQuery(sql);

		List userList = query.getResultList();
		return (ArrayList<UserDetails>) userList;

	}

	@Transactional
	public boolean updateUserStatus(String serialNumberCustomer, String status) {
		try {
			Session session = sessionFactory.getCurrentSession();

			String hql = "update UserDetails UD set UD.status=:Status where UD.serialNumberCustomer=:SerialNumberCustomer";
			Query query = session.createQuery(hql);
			query.setParameter("Status", status);

			query.setInteger("SerialNumberCustomer", Integer.parseInt(serialNumberCustomer));

			query.executeUpdate();

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Transactional
	public boolean deleteUserStatus(String serialNumberCustomer) {
		try {
			Session session = sessionFactory.getCurrentSession();

			String sql = "delete from UserDetails UD where UD.serialNumberCustomer=:SerialNumberCustomer";

			Query query = session.createQuery(sql);
			query.setInteger("SerialNumberCustomer", Integer.parseInt(serialNumberCustomer));

			query.executeUpdate();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return false;
	}

	@Transactional
	public UserDetails userLoginDetails(String serialNumberCustomer) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "FROM UserDetails WHERE serialNumberCustomer=:SerialNumberCustomer";

		Query query = session.createQuery(sql);
		query.setParameter("SerialNumberCustomer", Integer.parseInt(serialNumberCustomer));
		List<UserDetails> userDetails = query.getResultList();

		if (userDetails.size() > 0) {
			return (UserDetails) userDetails.get(0);
		} else {
			return null;
		}

	}

	@Transactional
	public void saveUser(ArrayList<UserDetails> loginUser) {
		Session session = sessionFactory.getCurrentSession();

		try {

			// loginDetails.setRole("user");
			session.save(loginUser);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
