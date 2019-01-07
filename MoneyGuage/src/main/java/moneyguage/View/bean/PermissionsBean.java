package moneyguage.View.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import moneyguage.Service.bean.Permissions;

@Named
@SessionScoped
public class PermissionsBean implements Serializable {
	private Permissions permission;
	private boolean search=false;

	public Permissions getPermission() {
		return permission;
	}

	public void setPermission(Permissions permission) {
		this.permission = permission;
	}

	public boolean isSearch() {
		return search;
	}

	public void setSearch(boolean search) {
		this.search = search;
	}

}
