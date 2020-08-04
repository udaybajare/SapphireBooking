package com.sapphire.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sapphire.entity.CRPriseReadyStock;
import com.sapphire.entity.GlassPriceReadyStock;

@Repository
public class GlassPriceReadyStockDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public List<GlassPriceReadyStock> getPriceList() {
		Session session = sessionFactory.getCurrentSession();

		String queryStr = "from GlassPriceReadyStock";

		Query query = session.createQuery(queryStr);

		List<GlassPriceReadyStock> priceList = query.getResultList();

		return priceList;
	}

	
	@Transactional
	public boolean saveOrUpdate(GlassPriceReadyStock glassPrice) {
		boolean glassPriceListUpdated = true;
		try {
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(glassPrice);
		} catch (Exception ex) {
			ex.printStackTrace();
			glassPriceListUpdated = false;
		}

		return glassPriceListUpdated;
	}

	@Transactional
	public int getUnitPrice(String Sph, String Cyl, String columnToSelect) {
		Session session = sessionFactory.getCurrentSession();

		String queryStr = "Select " + columnToSelect + " from GlassPriceReadyStock  where Sph=:Sph and Cyl=:Cyl";

		Query query = session.createQuery(queryStr);
		query.setParameter("Sph", Sph);
		query.setParameter("Cyl", Cyl);
		int unitPriceStr = Integer.parseInt((String) query.getSingleResult());

		return unitPriceStr;
	}


	
}
