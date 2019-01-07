package moneyguage.Service.service;

import moneyguage.Model.Bean.DbUserPermission;
import moneyguage.Model.Repository.PermissionRepository;
import moneyguage.Service.bean.Permissions;

public class PermissionService {
	public boolean setNewPermissions(String username, Permissions permission) {
		SigninService signinService = new SigninService();
		String userProfileId = signinService.getUserProfileId(username);

		DbUserPermission dbUserPermission = PermissionRepository.getUserPermissions(userProfileId);
		if (permission.isTradingOption()) {
			dbUserPermission.setTradingOption("Y");
		} else {
			dbUserPermission.setTradingOption("N");
		}
		if (permission.isBuyingOption()) {
			dbUserPermission.setBuyingOption("Y");
		} else {
			dbUserPermission.setBuyingOption("N");
		}
		if (permission.isSellingOption()) {
			dbUserPermission.setSellingOption("Y");
		} else {
			dbUserPermission.setSellingOption("N");
		}
		if (permission.isAdmin()) {
			dbUserPermission.setAdmin("Y");
		} else {
			dbUserPermission.setAdmin("N");
		}
		boolean status = PermissionRepository.updatePermissions(dbUserPermission);
		return status;
	}

	public Permissions getUserPermissions(String userProfileId) {
		DbUserPermission dbUserPermission = PermissionRepository.getUserPermissions(userProfileId);
		Permissions permissions = new Permissions();
		if (dbUserPermission != null) {
			if (dbUserPermission.getAdmin().equalsIgnoreCase("Y")) {
				permissions.setAdmin(true);
			} else {
				permissions.setAdmin(false);
			}
			if (dbUserPermission.getTradingOptions().equalsIgnoreCase("Y")) {
				permissions.setTradingOption(true);
			} else {
				permissions.setTradingOption(false);
			}
			if (dbUserPermission.getSellingOption().equalsIgnoreCase("y")) {
				permissions.setSellingOption(true);
			} else {
				permissions.setSellingOption(false);
			}
			if (dbUserPermission.getBuyingOption().equalsIgnoreCase("Y")) {
				permissions.setBuyingOption(true);
			} else {
				permissions.setBuyingOption(false);
			}
		}
		return permissions;
	}
}
