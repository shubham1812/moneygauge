package moneyguage.View.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import moneyguage.Service.bean.Permissions;
import moneyguage.Service.bean.UserPortfolio;

@Named
@SessionScoped
public class WebUserBean implements Serializable {

	private String userName;
	private boolean valid;
	private String emailId;
	private String userProfileId;
	private UserPortfolio userPortfolio;
	private float balance;
	private Permissions permissions;
	public UserPortfolio getUserPortfolio() {
		return userPortfolio;
	}

	public void setUserPortfolio(UserPortfolio userPortfolio) {
		this.userPortfolio = userPortfolio;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(String userProfileId) {
		this.userProfileId = userProfileId;
	}

	public Permissions getPermissions() {
		return permissions;
	}

	public void setPermissions(Permissions permissions) {
		this.permissions = permissions;
	}
}
