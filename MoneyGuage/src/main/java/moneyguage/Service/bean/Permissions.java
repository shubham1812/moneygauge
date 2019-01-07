package moneyguage.Service.bean;

public class Permissions {
	private boolean tradingOption;
	private boolean buyingOption;
	private boolean sellingOption;
	private boolean admin;

	public boolean isTradingOption() {
		return tradingOption;
	}

	public void setTradingOption(boolean tradingOption) {
		this.tradingOption = tradingOption;
	}

	public boolean isBuyingOption() {
		return buyingOption;
	}

	public void setBuyingOption(boolean buyingOption) {
		this.buyingOption = buyingOption;
	}

	public boolean isSellingOption() {
		return sellingOption;
	}

	public void setSellingOption(boolean sellingOption) {
		this.sellingOption = sellingOption;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
