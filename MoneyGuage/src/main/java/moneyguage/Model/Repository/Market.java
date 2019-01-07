package moneyguage.Model.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import moneyguage.Model.Bean.DbMarket;
import moneyguage.Model.Util.HibernateUtil;

public class Market {

	public static List<DbMarket> getStockData(String symbol, int numberOfValues) {
		Session session = HibernateUtil.getSession();
		List<DbMarket> stocks = new ArrayList<DbMarket>();
		try {

			Criteria cr = session.createCriteria(DbMarket.class);
			System.out.println(cr);
			cr.add(Restrictions.eq("symbol", symbol));
			System.out.println(cr);
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String dateInString = "10/06/2018";
			Date date = formatter.parse(dateInString);
			// cr.add(Restrictions.ge("last_updated", date));
			// cr.add(Restrictions.ge("id", 620000));
			cr.addOrder(Order.desc("id"));
			cr.setMaxResults(numberOfValues);
			List<?> list = (List) cr.list();
			System.out.println(cr);
			System.out.println(symbol);
			System.out.println(list);
			for (int i = 0; i < list.size(); i++) {
				stocks.add((DbMarket) list.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return stocks;
	}

	@SuppressWarnings("deprecation")
	public static List<String> getDistinctStocks() {
		Session session = HibernateUtil.getSession();
		List<String> stocks = new ArrayList<String>();
		try {
			Criteria cr = session.createCriteria(DbMarket.class);
			cr.setProjection(Projections.groupProperty("symbol"));
			List lst = cr.list();
			for (int i = 0; i < lst.size(); i++) {
				stocks.add((String) lst.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return stocks;
	}

	@SuppressWarnings("deprecation")
	public static DbMarket getCurrentPrice(String symbol) {

		Session session = HibernateUtil.getSession();
		List<DbMarket> stocks = new ArrayList<DbMarket>();
		try {
			Criteria cr = session.createCriteria(DbMarket.class);
			cr.add(Restrictions.eq("symbol", symbol));
			cr.addOrder(Order.desc("id"));
			cr.setMaxResults(1);
			List<?> list = (List) cr.list();
			for (int i = 0; i < list.size(); i++) {
				stocks.add((DbMarket) list.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return stocks.get(0);
	}

}