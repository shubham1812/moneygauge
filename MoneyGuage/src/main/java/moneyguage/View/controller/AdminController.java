package moneyguage.View.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import moneyguage.Service.service.OrderService;
import moneyguage.Service.service.PermissionService;
import moneyguage.Service.service.SigninService;
import moneyguage.View.bean.AddMoneyBean;
import moneyguage.View.bean.AdminBean;
import moneyguage.View.bean.PermissionsBean;
import moneyguage.View.bean.WebUserBean;

@Named
@RequestScoped
public class AdminController {
	@Inject
	AdminBean adminBean;
	@Inject
	PermissionsBean permissionBean;
	@Inject
	SigninService signinService;
	@Inject
	OrderService orderService;
	@Inject
	PermissionService permissionService;
	@Inject
	AddMoneyBean addMoneyBean;
	@Inject
	WebUserBean webUserBean;

	public String search() {
		String userProfileId = signinService.getUserProfileId(adminBean.getUsername());
		adminBean.setUserPortfolio(orderService.getUserPortfolio(userProfileId));
		permissionBean.setPermission(permissionService.getUserPermissions(userProfileId));
		permissionBean.setSearch(true);
		return null;
	}

	public String setPermissions() {
		boolean status = permissionService.setNewPermissions(adminBean.getUsername(), permissionBean.getPermission());
		FacesContext context = FacesContext.getCurrentInstance();
		if (status) {
			context.addMessage(null, new FacesMessage("Successful", "Permissions have been successfully updated"));
		} else {
			context.addMessage(null, new FacesMessage("Error", "Error in updating permissions"));
		}
		return null;
	}

	public String checkIfUserAdmin() {
		if (!permissionService.getUserPermissions(webUserBean.getUserProfileId()).isAdmin()) {
			return "pretty:home";
		}
		return null;

	}

	public String addMoney() {
		String userProfileId = signinService.getUserProfileId(adminBean.getUsername());
		FacesContext context = FacesContext.getCurrentInstance();
		if (orderService.addMoney(userProfileId, addMoneyBean.getAmount())) {
			context.addMessage(null, new FacesMessage("Successful", "Money successfully added"));
		} else {
			context.addMessage(null, new FacesMessage("Error", "Error in adding money"));
		}
		return null;
	}
}
