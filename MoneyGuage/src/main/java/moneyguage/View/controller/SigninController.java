package moneyguage.View.controller;

import java.io.IOException;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;

import com.ocpsoft.pretty.PrettyContext;

import moneyguage.Service.Util.PasswordEncryption;
import moneyguage.Service.Util.SocialProvider;
import moneyguage.Service.bean.Permissions;
import moneyguage.Service.bean.StockData;
import moneyguage.Service.bean.WebUserProfile;
import moneyguage.Service.service.OrderService;
import moneyguage.Service.service.PermissionService;
import moneyguage.Service.service.RegistrationService;
import moneyguage.Service.service.SigninService;
import moneyguage.View.bean.LoginBean;
import moneyguage.View.bean.RegistrationBean;
import moneyguage.View.bean.WebUserBean;

@Named
@RequestScoped
public class SigninController {

	@Inject
	LoginBean loginBean;

	@Inject
	RegistrationController registrationController;

	@Inject
	RegistrationBean registrationBean;

	@Inject
	WebUserBean webUserBean;

	@Inject
	SigninService signinService;

	@Inject
	PermissionService permissionService;
	
	@Inject
	SocialProvider socialProvider;

	@Inject
	RegistrationService registrationService;

	@Inject
	OrderService orderService;

	public String signInUser() {
		String redirect = null;
		redirect = loginExistingUser(loginBean.getUserName(), loginBean.getPassword());
		return redirect;
	}

	String loginExistingUser(String userName, String password) {
		String userProfileId = signinService.signinUser(userName, password);
		return loginUser(userProfileId);
	}

	private String loginUser(String userProfileId) {
		String redirect = null;
		if (userProfileId != null) {
			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			HttpSession httpSession = request.getSession();
			Permissions permission = permissionService.getUserPermissions(userProfileId);
			setUserInContext(httpSession, userProfileId, permission);
			String page = (String) request.getParameter("page");
			if (!("".equals(page) || "null".equals(page) || null == page)
					|| (PrettyContext.getCurrentInstance().getCurrentMapping() != null)) {
				redirect = "pretty:home";
				httpSession.removeAttribute("LOGIN_REDIRECT_PAGE");
			} else {
				redirect = "pretty:home";
			}
			if (permission.isAdmin()) {
				redirect = "pretty:admin";
			}
		} else {
			webUserBean.setValid(false);
			FacesContext.getCurrentInstance().addMessage("invalidIdPassword",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Username/Password", ""));
		}
		return redirect;
	}

	public WebUserProfile setUserInContext(HttpSession httpSession, String userProfileId, Permissions permission) {
		WebUserProfile webUserProfile = signinService.getWebUserDetails(userProfileId);
		if (webUserProfile != null) {
			webUserBean.setValid(true);
			webUserBean.setUserName(webUserProfile.getFirstName() + " " + webUserProfile.getLastName());
			webUserBean.setEmailId(webUserProfile.getEmailId());
			webUserBean.setUserProfileId(userProfileId);
			webUserBean.setUserPortfolio(orderService.getUserPortfolio(webUserBean.getUserProfileId()));
			if (webUserBean.getUserPortfolio() != null) {
				for (StockData stock : webUserBean.getUserPortfolio().getStocks()) {
					if (stock.getStock().equalsIgnoreCase("USD")) {
						webUserBean.setBalance(stock.getTotalAmount());
					}
				}
			}
			webUserBean.setPermissions(permission);
			httpSession.setAttribute("USER_LOGGED_IN", true);
		}
		return webUserProfile;
	}

	public void logout() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession session = request.getSession();
		try {
			session.removeAttribute("USER_LOGGED_IN");
			session.invalidate();
			FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + "/home");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void signout() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession session = request.getSession();
		try {
			session.removeAttribute("USER_LOGGED_IN");
			session.invalidate();
			FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() + "/home");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String socialUrl(String provider) {
		return socialProvider.buildUrl(provider);
	}

	String loginExistingSocialUser(String userName) {
		String userProfileId = signinService.signinUser(userName);
		return loginUser(userProfileId);
	}

	public String callback() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession session = request.getSession();
		String provider = request.getParameter("provider");
		String code = request.getParameter("code");
		String page = request.getParameter("page");
		Map<String, String> socialData = socialProvider.getSocialData(code, provider);
		// Register or login the user based on email of user.
		if (socialData == null) {
			return "pretty:signin";
		}
		if (socialData.get("email") != null) {
			loginBean.setUserName(socialData.get("email"));
		}
		if (registrationService.checkIfUserExists(socialData.get("email"))) {
			return loginExistingSocialUser(socialData.get("email"));
		} else {
			String gender = socialData.get("gender");
			if (gender != null && gender.equalsIgnoreCase("male")) {
				registrationBean.getWebUserProfile().setTitle("Mr");
			} else if (gender != null && gender.equalsIgnoreCase("female")) {
				registrationBean.getWebUserProfile().setTitle("Ms");
			}
			registrationBean.getWebUserProfile().setRegistrationSource(provider.toUpperCase());
			registrationBean.getWebUserProfile().setFirstName(socialData.get("first_name"));
			registrationBean.getWebUserProfile().setLastName(socialData.get("last_name"));
			registrationBean.getWebUserProfile().setEmailId(socialData.get("email"));
			registrationBean.getWebUserProfile().setPhoneNo(socialData.get("phone"));
			String newPassword = RandomStringUtils.random(8, true, true);

			return registrationController.registerNewUser(registrationBean.getWebUserProfile(),
					PasswordEncryption.generateSecurePassword(newPassword, "portfolio"));

		}

	}

}
