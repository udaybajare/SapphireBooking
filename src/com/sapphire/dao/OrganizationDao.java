package com.sapphire.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
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
	public int getMaxCustNumber(){
		Session session = sessionFactory.getCurrentSession();
		String sql = "select max(custNumber) from OrganizationDetails";
		Query qry = session.createQuery(sql);

		String maxCustNo = (String)qry.uniqueResult();
	

		return Integer.valueOf(maxCustNo);	
	}
	@Transactional
	public ArrayList<ArrayList<String>> getRegisteredOrganization() {
		
		ArrayList<ArrayList<String>> registeredOrgs = null;
		try {
			Session session = sessionFactory.getCurrentSession();

			Criteria criteria = session.createCriteria(OrganizationDetails.class);
			ProjectionList projList = Projections.projectionList();
			projList.add(Projections.property("custNumber"));
			projList.add(Projections.property("orgName"));
			criteria.setProjection(projList);
			/*
			 * criteria.setProjection(Projections.property("orgName"));
			 * criteria.setProjection(Projections.property("custNumber" "-"
			 * "orgName"));
			 */

	    registeredOrgs = (ArrayList<ArrayList<String>>) criteria.list();

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
