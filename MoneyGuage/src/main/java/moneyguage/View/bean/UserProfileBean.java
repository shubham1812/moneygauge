package moneyguage.View.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import moneyguage.Service.bean.StockData;

@Named
@SessionScoped
public class UserProfileBean implements Serializable {

	private String name;
	private String email;
	private String phoneNo;
	private List<StockData> orders;
	private float netWorth;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public List<StockData> getOrders() {
		return orders;
	}

	public void setOrders(List<StockData> orders) {
		this.orders = orders;
	}

	public float getNetWorth() {
		return netWorth;
	}

	public void setNetWorth(float netWorth) {
		this.netWorth = netWorth;
	}

}
