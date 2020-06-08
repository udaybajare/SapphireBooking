package com.sapphire.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sapphire.entity.CRPrise;

@Repository
public class CRPriceDao 
{

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public List<CRPrise> getUnitPrice(String type, String tint, String index) 
	{
		Session session = sessionFactory.getCurrentSession();
			
		System.out.println("String "+type+", String "+tint+", String "+index);
		String queryStr = "from CRPrise CR where CR.product=:product and CR.type=:type and CR.indexVal=:index";

		Query query = session.createQuery(queryStr);
		
		query.setParameter("product", type);
		query.setParameter("type", tint);
		query.setParameter("index", index);
		
		List<CRPrise> crPriceList = query.getResultList();

		return crPriceList;
	}
	
	@Transactional
	public boolean saveOrUpdate(CRPrise crPrice) 
	{
		boolean crPriceListUpdated = true; 
		try{
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(crPrice);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			crPriceListUpdated = false;
		}
		
		return crPriceListUpdated;
	}
	
	@Transactional
	public List<CRPrise> getPriceList() 
	{
		Session session = sessionFactory.getCurrentSession();
			
		String queryStr = "from CRPrise";

		Query query = session.createQuery(queryStr);
		
		List<CRPrise> crPriceList = query.getResultList();

		return crPriceList;
	}
}
