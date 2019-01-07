package moneyguage.Model.Repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import moneyguage.Model.Bean.DbPortfolioOrders;
import moneyguage.Model.Bean.DbUserAccess;
import moneyguage.Model.Bean.DbUserPermission;
import moneyguage.Model.Bean.DbUserPortfolio;
import moneyguage.Model.Bean.DbWebUserProfile;
import moneyguage.Model.Util.HibernateUtil;
import moneyguage.Service.Util.UniqueIdGenerator;
import moneyguage.Service.bean.WebUserProfile;

public class UserAuthentication {

	@SuppressWarnings("deprecation")
	public static DbUserAccess getUserByUserId(String username) {
		Session session = HibernateUtil.getSession();
		DbUserAccess user = null;
		try {
			Criteria cr = session.createCriteria(DbUserAccess.class);
			cr.add(Restrictions.eq("username", username));
			List<?> list = (List) cr.list();
			if (list != null && list.size() > 0) {
				user = (DbUserAccess) list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}

	@SuppressWarnings("deprecation")
	public static boolean isUserExists(String username) {
		Session session = HibernateUtil.getSession();
		boolean result = false;
		DbUserAccess user = null;
		try {
			Criteria cr = session.createCriteria(DbUserAccess.class);
			cr.add(Restrictions.eq("username", username));
			List<?> list = (List) cr.list();
			if (list != null && list.size() > 0) {
				user = (DbUserAccess) list.get(0);
			}
			if (user != null)
				result = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return result;
	}

	@SuppressWarnings("deprecation")
	public static DbUserAccess findUser(String username) {
		Session session = HibernateUtil.getSession();
		DbUserAccess user = null;
		try {
			Criteria cr = session.createCriteria(DbUserAccess.class);
			cr.add(Restrictions.eq("username", username));
			List<?> list = (List) cr.list();
			if (list != null && list.size() > 0) {
				user = (DbUserAccess) list.get(0);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}

	public static boolean register(DbWebUserProfile user, DbUserAccess dbUserAccess) {
		DbUserPortfolio dbUserPortfolio = new DbUserPortfolio();
		dbUserPortfolio.setPrice(0);
		dbUserPortfolio.setStockName("USD");
		dbUserPortfolio.setSymbol("USD");
		dbUserPortfolio.setTotalAmount(1000);
		dbUserPortfolio.setUserProfileId(dbUserAccess.getUserProfileId());
		dbUserPortfolio.setVolume(0);

		DbPortfolioOrders dbPortfolioOrders = new DbPortfolioOrders();
		dbPortfolioOrders.setOrderId(UniqueIdGenerator.generateUniqueId());
		dbPortfolioOrders.setOrderType("BAL");
		dbPortfolioOrders.setPrice(0);
		dbPortfolioOrders.setStockName("USD");
		dbPortfolioOrders.setSymbol("USD");
		dbPortfolioOrders.setTotalAmount(1000);
		dbPortfolioOrders.setUserProfileId(dbUserAccess.getUserProfileId());
		dbPortfolioOrders.setVolume(0);

		DbUserPermission dbUserPermission = new DbUserPermission();
		dbUserPermission.setAdmin("N");
		dbUserPermission.setBuyingOption("Y");
		dbUserPermission.setSellingOption("Y");
		dbUserPermission.setTradingOption("Y");
		dbUserPermission.setUserProfileId(user.getUserProfileId().toString());
		boolean status = true;
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();
			session.save(user);
			session.save(dbUserAccess);
			session.save(dbUserPortfolio);
			session.save(dbPortfolioOrders);
			session.save(dbUserPermission);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		} finally {
			session.close();
		}
		return status;
	}

	@SuppressWarnings("deprecation")
	public static DbWebUserProfile getWebUserDetailsByUserProfileId(Long userProfileId) {
		Session session = HibernateUtil.getSession();
		DbWebUserProfile user = null;
		try {
			Criteria cr = session.createCriteria(DbWebUserProfile.class);
			cr.add(Restrictions.eq("userProfileId", userProfileId));
			List<?> list = (List) cr.list();
			if (list != null && list.size() > 0) {
				user = (DbWebUserProfile) list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}

	public static void resetOtp(String forgotPasswordEmail, String otp) {
		DbUserAccess dbUserAccess = UserAuthentication.findUser(forgotPasswordEmail);
		dbUserAccess.setOtp(otp);
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();
			session.update(dbUserAccess);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static void resetPassword(String forgotPasswordEmail, String newPassword) {
		DbUserAccess dbUserAccess = UserAuthentication.findUser(forgotPasswordEmail);
		dbUserAccess.setPassword(newPassword);
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();
			session.update(dbUserAccess);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
