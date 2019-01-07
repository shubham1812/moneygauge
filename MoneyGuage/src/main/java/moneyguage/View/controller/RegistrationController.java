package moneyguage.View.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import moneyguage.Service.bean.Permissions;
import moneyguage.Service.bean.WebUserProfile;
import moneyguage.Service.service.PermissionService;
import moneyguage.Service.service.RegistrationService;
import moneyguage.View.bean.RegistrationBean;
import moneyguage.View.bean.WebUserBean;

@Named
@RequestScoped
public class RegistrationController {

	@Inject
	RegistrationBean registrationBean;

	@Inject
	WebUserBean webUserBean;

	@Inject
	RegistrationService registrationService;

	@Inject
	SigninController loginController;
	@Inject
	PermissionService permissionService;

	public String registerUser() {
		String redirectUrl = null;
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession session = request.getSession();
		/*
		 * String recaptcha = request.getParameter("g-recaptcha-response");
		 * VerifyRecaptcha verifyRecaptcha = new
		 * VerifyRecaptcha(appContext.getApplicationId());
		 */
		if (!registrationService.checkIfUserExists(registrationBean.getWebUserProfile().getEmailId())) {
			redirectUrl = registerNewUser(registrationBean.getWebUserProfile(), registrationBean.getPassword());
		} else {
			webUserBean.setValid(false);
			FacesContext.getCurrentInstance().addMessage("userAlreadyExists",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email ID already Exists", ""));
		}

		return redirectUrl;
	}

	public String registerNewUser(WebUserProfile userProfile, String password) {

		String redirectUrl = null;
		String userProfileId = registrationService.saveUser(userProfile, password);
		if (userProfileId != null) {
			registrationBean.setTwoFactorMailSent(true);
			/*
			 * HttpServletRequest request = (HttpServletRequest)
			 * FacesContext.getCurrentInstance().getExternalContext() .getRequest();
			 * HttpSession httpSession = request.getSession();
			 * loginController.setUserInContext(httpSession, userProfileId); String page =
			 * request.getParameter("page"); if (!("".equals(page) || "null".equals(page) ||
			 * null == page)) { redirectUrl = page + "?faces-redirect=true";
			 * httpSession.removeAttribute("LOGIN_REDIRECT_PAGE"); } else { redirectUrl =
			 * "pretty:home"; }
			 */} else {
			FacesContext.getCurrentInstance().addMessage("userAlreadyExists",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to create user", ""));
		}
		return redirectUrl;
	}

	public String submitOtp() {
		String redirectUrl = null;
		String userProfileId = registrationService.checkIfOtpTrue(registrationBean.getWebUserProfile(),
				registrationBean.getOtp());
		if (userProfileId != null) {
			registrationBean.setTwoFactorMailSent(false);

			HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
					.getRequest();
			HttpSession httpSession = request.getSession();
			Permissions permission = permissionService.getUserPermissions(userProfileId);
			loginController.setUserInContext(httpSession, userProfileId, permission);
			String page = request.getParameter("page");
			if (!("".equals(page) || "null".equals(page) || null == page)) {
				redirectUrl = page + "?faces-redirect=true";
				httpSession.removeAttribute("LOGIN_REDIRECT_PAGE");
			} else {
				redirectUrl = "pretty:home";
			}
		} else {
			FacesContext.getCurrentInstance().addMessage("userAlreadyExists",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to create user", ""));
		}
		return redirectUrl;
	}
}
