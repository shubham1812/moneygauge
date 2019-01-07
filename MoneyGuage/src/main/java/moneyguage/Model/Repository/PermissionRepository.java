package moneyguage.Model.Repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import moneyguage.Model.Bean.DbUserPermission;
import moneyguage.Model.Util.HibernateUtil;

public class PermissionRepository {

	public static DbUserPermission getUserPermissions(String userProfileId) {
		Session session = HibernateUtil.getSession();
		DbUserPermission user = null;
		try {
			Criteria cr = session.createCriteria(DbUserPermission.class);
			cr.add(Restrictions.eq("userProfileId", userProfileId));
			List<?> list = (List) cr.list();
			if (list != null && list.size() > 0) {
				user = (DbUserPermission) list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}

	public static boolean updatePermissions(DbUserPermission dbUserPermission) {
		boolean status = true;
		Session session = HibernateUtil.getSession();
		try {
			session.beginTransaction();
			session.update(dbUserPermission);
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
