package com.sapphire.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GlassPriceDao 
{
	public GlassPriceDao()
	{
	}
	
	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public int getUnitPrice(int rowIndex, String columnToSelect) {
		Session session = sessionFactory.getCurrentSession();

		String queryStr = "Select GP."+columnToSelect+" from GlassPrice GP where GP.rowIndex=:rowIndex";

		Query query = session.createQuery(queryStr);
		query.setParameter("rowIndex", rowIndex);

		int unitPriceStr = Integer.parseInt((String)query.getSingleResult());

		return unitPriceStr;
	}
}
