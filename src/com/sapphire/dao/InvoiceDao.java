package com.sapphire.dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sapphire.entity.InvoiceDetails;

@Repository
public class InvoiceDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void saveInvoice(InvoiceDetails invoiceDetail) 
	{
		
		Session session = sessionFactory.getCurrentSession();

		try {
			session.save(invoiceDetail);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Transactional
	public String getInvoiceNo() {
		Session session = sessionFactory.getCurrentSession();

		String sql = "SELECT invoiceNo FROM InvoiceDetails ORDER BY STR_TO_DATE(invoiceGenerateDate, '%Y-%m-%d %H:%i:%S')  DESC";

		Query qry = session.createQuery(sql);

		List invoiceNo = qry.getResultList();
		

		return (String)invoiceNo.get(0);
	}

	@Transactional
	public InvoiceDetails getInvoiceDetails(String invoiceNo)
	{
		Session session = sessionFactory.getCurrentSession();
		
		String sql = "from InvoiceDetails where invoiceNo=:invoiceNo";
		
		Query query = session.createQuery(sql);
		InvoiceDetails result = (InvoiceDetails) query.uniqueResult();
		
		
		return result;
	}
}
