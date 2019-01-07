package moneyguage.View.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import moneyguage.Service.bean.WebUserProfile;

@Named
@SessionScoped
public class RegistrationBean implements Serializable {

	private String password;
	private WebUserProfile webUserProfile = new WebUserProfile();
	private boolean twoFactorMailSent;
	private String otp;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public WebUserProfile getWebUserProfile() {
		return webUserProfile;
	}

	public void setWebUserProfile(WebUserProfile webUserProfile) {
		this.webUserProfile = webUserProfile;
	}

	public boolean isTwoFactorMailSent() {
		return twoFactorMailSent;
	}

	public void setTwoFactorMailSent(boolean twoFactorMailSent) {
		this.twoFactorMailSent = twoFactorMailSent;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

}
