package moneyguage.Service.bean;

import java.util.List;

public class UserPortfolio {
	private String userProfileId;
	private List<StockData> stocks;
	public String getUserProfileId() {
		return userProfileId;
	}
	public void setUserProfileId(String userProfileId) {
		this.userProfileId = userProfileId;
	}
	public List<StockData> getStocks() {
		return stocks;
	}
	public void setStocks(List<StockData> stocks) {
		this.stocks = stocks;
	}
	
}
