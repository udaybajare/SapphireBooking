package com.sapphire.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sapphire.entity.EntryDetails;
import com.sapphire.entity.OrderDetails;

@Repository
public class OrderDao {

	public OrderDao() {
	}

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void saveInventory(List<OrderDetails> orderDetails, List<EntryDetails> entryDetails) {
		Session session = sessionFactory.getCurrentSession();

		int orderId = getMaxOrderId() + 1;
		try {
			for (int i = 0; i < orderDetails.size(); i++) {
				orderDetails.get(i).setOrderId(orderId);
				session.saveOrUpdate(orderDetails.get(i));

				entryDetails.get(i).setOrderId(orderId);
				entryDetails.get(i).setOrderDetailsId(orderDetails.get(i).getId());
				session.saveOrUpdate(entryDetails.get(i));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Transactional
	public int getMaxOrderId() {
		Object obj = null;
		try {
			Session session = sessionFactory.getCurrentSession();

			String sql = "select max(orderId) FROM OrderDetails";
			Query qry = session.createQuery(sql);
			
			obj = qry.uniqueResult();
		} catch (Exception ex) {
			//Log it somewhere
		}
		int maxOrderId = (obj != null) ? (int) obj : 0;

		return maxOrderId;
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
	public ArrayList<OrderDetails> getAllOrders(int orderId) {
		Session session = sessionFactory.getCurrentSession();

		String hql = "";

		hql = "from OrderDetails OD where OD.orderId=" + orderId;

		Query query = session.createQuery(hql);
		List<OrderDetails> OrderDetailsList = query.list();

		return (ArrayList<OrderDetails>) OrderDetailsList;
	}

	@Transactional
	public ArrayList<Integer> getAllOrdersIds() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select DISTINCT orderId from OrderDetails";

		Query query = session.createQuery(hql);
		List<Integer> OrderDetailsList = query.list();

		return (ArrayList<Integer>) OrderDetailsList;
	}

	@Transactional
	public ArrayList<Integer> getAllOrdersIds(String selector, String selectorValue) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "";

		if (selector.equalsIgnoreCase("orderDate")) {
			String[] dateRange = selectorValue.split("-");

			hql = "select DISTINCT orderId from OrderDetails OD where OD." + selector + " BETWEEN '" + dateRange[0]
					+ "' AND '" + dateRange[1] + "'";
		} else {
			if (!selector.equals("")) {
				hql = "select DISTINCT orderId from OrderDetails OD where OD." + selector + "='" + selectorValue + "'";
			} else {
				hql = "select DISTINCT orderId from OrderDetails";
			}

		}

		System.out.println("SQL query is : " + hql);

		Query query = session.createQuery(hql);
		List<Integer> OrderDetailsList = query.list();

		return (ArrayList<Integer>) OrderDetailsList;
	}

	@Transactional
	public boolean updateStatus(String orderId, String status) {
		try {
			Session session = sessionFactory.getCurrentSession();

			String hql = " update OrderDetails OD set OD.status = :Status where OD.orderId=:OrderId";

			Query query = session.createQuery(hql);
			query.setParameter("Status", status);
			query.setInteger("OrderId", Integer.parseInt(orderId));

			query.executeUpdate();

			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Transactional
	public EntryDetails getEntryDetails(int orderId, int orderDetailsId) {
		EntryDetails entryDetailsList = new EntryDetails();
		Session session = sessionFactory.getCurrentSession();
		String hql = " from EntryDetails ED where ED.orderId=:orderId and ED.orderDetailsId=:orderDetailsId";
		Query query = session.createQuery(hql);
		query.setInteger("orderId", orderId);
		query.setInteger("orderDetailsId", orderDetailsId);

		entryDetailsList = (EntryDetails) query.uniqueResult();
		return entryDetailsList;
	}
}
