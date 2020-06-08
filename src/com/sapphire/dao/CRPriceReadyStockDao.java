package com.sapphire.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.jboss.jandex.IndexView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sapphire.entity.CRPrise;
import com.sapphire.entity.CRPriseReadyStock;

@Repository
public class CRPriceReadyStockDao {

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public List<CRPriseReadyStock> getUnitPrice(String type, String tint, String index, String coating, String dia,
			String axis) {
		Session session = sessionFactory.getCurrentSession();

		System.out.println("String " + type + ", String " + tint + ", String " + index);

		if (null != coating && !(coating.equalsIgnoreCase("Blue Protect Green Coat"))) {
			coating = "";
		}

		if (null != index && !(index.equalsIgnoreCase("1.59 Polycarbonate"))) {
			index = "";
		}
		
		switch (axis) {
		case "45":
		case "90":
		case "135":
		case "180":
			;
		default:
			axis = "";
			break;
		}

		String queryStr = "from CRPriseReadyStock CR where " + "CR.typeVal=:typeVal and " + "CR.tint=:tint and "
				+ "CR.indexVal=:index and " + "CR.coating=:coating and " + "CR.dia=:dia and " + "CR.axis=:axis";

		Query query = session.createQuery(queryStr);

		query.setParameter("typeVal", type);
		query.setParameter("tint", tint);
		query.setParameter("index", index);
		query.setParameter("coating", coating);
		query.setParameter("dia", dia);
		query.setParameter("axis", axis);

		List<CRPriseReadyStock> crPriceList = query.getResultList();

		return crPriceList;
	}

	@Transactional
	public boolean saveOrUpdate(CRPriseReadyStock crPrice) {
		boolean crPriceListUpdated = true;
		try {
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(crPrice);
		} catch (Exception ex) {
			ex.printStackTrace();
			crPriceListUpdated = false;
		}

		return crPriceListUpdated;
	}

	@Transactional
	public List<CRPriseReadyStock> getPriceList() {
		Session session = sessionFactory.getCurrentSession();

		String queryStr = "from CRPriseReadyStock";

		Query query = session.createQuery(queryStr);

		List<CRPriseReadyStock> crPriceList = query.getResultList();

		return crPriceList;
	}
}
