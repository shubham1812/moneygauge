package moneyguage.Model.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import moneyguage.Model.Bean.DbMarket;
import moneyguage.Model.Bean.DbPortfolioOrders;
import moneyguage.Model.Bean.DbUserPortfolio;
import moneyguage.Model.Util.HibernateUtil;

public class MarketRepository {

	@SuppressWarnings("deprecation")
	public static List<DbUserPortfolio> getUserPortfolio(String userProfileId, String stockName) {
		Session session = HibernateUtil.getSession();
		List<DbUserPortfolio> dbPortfolioOrders = new ArrayList<DbUserPortfolio>();
		try {
			Criteria cr = session.createCriteria(DbUserPortfolio.class);
			cr.add(Restrictions.eq("userProfileId", Long.valueOf(userProfileId)));
			cr.addOrder(Order.desc("creation_date_time"));
			if (stockName != null) {
				Criterion rest1 = Restrictions.eq("stockName", stockName);
				Criterion rest2 = Restrictions.eq("stockName", "USD");
				cr.add(Restrictions.or(rest1, rest2));
			}
			List<?> list = (List) cr.list();
			if (list != null && list.size() > 0) {
				Iterator itr = list.iterator();
				while (itr.hasNext()) {
					DbUserPortfolio emp = (DbUserPortfolio) itr.next();
					dbPortfolioOrders.add(emp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return dbPortfolioOrders;
	}

	@SuppressWarnings("deprecation")
	public static List<DbMarket> getAllStock(String tickerId) {
		Session session = HibernateUtil.getSession();
		List<DbMarket> stocks = new ArrayList<DbMarket>();
		try {
			Criteria cr = session.createCriteria(DbMarket.class);
			cr.add(Restrictions.eq("ticker_id", tickerId));
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String dateInString = "10/06/2018";
			Date date = formatter.parse(dateInString);
			cr.addOrder(Order.desc("id"));
			cr.setMaxResults(50);
			List<?> list = (List) cr.list();
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
	public static boolean placeOrder(DbPortfolioOrders dbStockOrder, DbPortfolioOrders dbStockDataPrice,
			DbUserPortfolio dbUsdPrice, DbUserPortfolio dbUserPortfolio) {
		List<DbUserPortfolio> dbPortfolioOrders = getUserPortfolio(dbStockOrder.getUserProfileId().toString(),
				dbStockOrder.getStockName());
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		boolean flag = true;
		boolean status = true;
		try {
			for (DbUserPortfolio dbOrders : dbPortfolioOrders) {
				if (dbOrders.getStockName().equalsIgnoreCase("USD")) {
					dbOrders.setTotalAmount(dbUsdPrice.getTotalAmount());
				} else if (dbOrders.getStockName().equalsIgnoreCase(dbUserPortfolio.getStockName())) {
					dbOrders.setTotalAmount(dbUserPortfolio.getTotalAmount());
					dbOrders.setPrice(dbUserPortfolio.getPrice());
					dbOrders.setVolume(dbUserPortfolio.getVolume());
					flag = false;
				}
				session.saveOrUpdate(dbOrders);
			}
			if (flag) {
				session.save(dbUserPortfolio);
			}
			session.save(dbStockOrder);
			session.save(dbStockDataPrice);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			status = false;

		} finally {
			session.close();
		}
		return status;
	}

	public static List<DbPortfolioOrders> getUserOrders(Long userProfileId) {
		Session session = HibernateUtil.getSession();
		List<DbPortfolioOrders> stocks = new ArrayList<DbPortfolioOrders>();
		try {
			Criteria cr = session.createCriteria(DbPortfolioOrders.class);
			cr.add(Restrictions.eq("userProfileId", userProfileId));
			cr.addOrder(Order.desc("id"));
			List<?> list = (List) cr.list();
			for (int i = 0; i < list.size(); i++) {
				stocks.add((DbPortfolioOrders) list.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return stocks;
	}

	public static List<DbPortfolioOrders> getStockOrders(String symbol) {
		Session session = HibernateUtil.getSession();
		List<DbPortfolioOrders> stocks = new ArrayList<DbPortfolioOrders>();
		try {
			Criteria cr = session.createCriteria(DbPortfolioOrders.class);
			cr.add(Restrictions.eq("symbol", symbol));
			cr.addOrder(Order.desc("id"));
			List<?> list = (List) cr.list();
			for (int i = 0; i < list.size(); i++) {
				stocks.add((DbPortfolioOrders) list.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return stocks;
	}

	public static boolean addMoney(DbUserPortfolio user, DbPortfolioOrders dbPortfolioOrders) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		boolean status = true;
		try {
			session.saveOrUpdate(user);
			session.save(dbPortfolioOrders);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		} finally {
			session.close();
		}
		return status;
	}
}
