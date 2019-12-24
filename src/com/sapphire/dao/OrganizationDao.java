package com.sapphire.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sapphire.entity.OrganizationDetails;

@Repository
public class OrganizationDao {

	public OrganizationDao() {

	}

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public boolean addOrganization(OrganizationDetails orgDetails) {
		boolean added = false;

		try {
			Session session = sessionFactory.getCurrentSession();

			session.save(orgDetails);
			added = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return added;
	}

	@Transactional
	public ArrayList<String> getRegisteredOrganization() {
		ArrayList<String> registeredOrgs = new ArrayList<String>();
		try {
			Session session = sessionFactory.getCurrentSession();

			Criteria criteria = session.createCriteria(OrganizationDetails.class);
			criteria.setProjection(Projections.property("orgName"));

			registeredOrgs = (ArrayList<String>) criteria.list();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return registeredOrgs;
	}

	@Transactional
	public boolean searchOrganization(String orgName) {

		boolean orgExists = false;

		try {
			Session session = sessionFactory.getCurrentSession();

			String selectHql = "FROM OrganizationDetails OG where OG.orgName=:orgName";
			Query query = session.createQuery(selectHql);

			query.setParameter("orgName", orgName);

			List orgList = query.list();

			if (orgList.size() > 0) {
				orgExists = true;
			}
		} catch (Exception e) {
			// search = false;
			e.printStackTrace();

		}
		return orgExists;
	}
}
