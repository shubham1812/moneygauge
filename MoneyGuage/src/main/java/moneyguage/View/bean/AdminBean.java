package moneyguage.View.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import moneyguage.Service.bean.UserPortfolio;

@Named
@SessionScoped
public class AdminBean implements Serializable {
	private String username;
	private UserPortfolio userPortfolio;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserPortfolio getUserPortfolio() {
		return userPortfolio;
	}

	public void setUserPortfolio(UserPortfolio userPortfolio) {
		this.userPortfolio = userPortfolio;
	}

}
