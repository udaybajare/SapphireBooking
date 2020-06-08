package com.sapphire.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sapphire.entity.GlassPrice;

@Repository
public class GlassPriceDao {
	public GlassPriceDao() {
	}

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public int getUnitPrice(int rowIndex, String columnToSelect) {
		Session session = sessionFactory.getCurrentSession();

		String queryStr = "Select GP." + columnToSelect + " from GlassPrice GP where GP.rowIndex=:rowIndex";

		Query query = session.createQuery(queryStr);
		query.setParameter("rowIndex", rowIndex);

		int unitPriceStr = Integer.parseInt((String) query.getSingleResult());

		return unitPriceStr;
	}

	@Transactional
	public boolean saveOrUpdate(GlassPrice glassPrice) {

		boolean result = true;
		try {
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(glassPrice);
		} catch (Exception ex) {
			ex.printStackTrace();
			result = false;
		}
		return result;
	}

	@Transactional
	public List<GlassPrice> getPriceList() {
		Session session = sessionFactory.getCurrentSession();

		String queryStr = "from GlassPrice gp where gp.rowIndex > 4";

		Query query = session.createQuery(queryStr);

		List<GlassPrice> priceList = query.getResultList();

		return priceList;
	}
}
