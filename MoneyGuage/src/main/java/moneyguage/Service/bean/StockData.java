package moneyguage.Service.bean;

import java.util.Date;

public class StockData {
	private String orderId;
	private String orderType;
	private float price;
	private float volume;
	private float totalAmount;
	private Date creationDateTime;
	private String stock;
	private String symbol;
	private float currentPrice;
	private boolean profit;
	private float changePercentage;
	private float changeAmount;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}


	public Date getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public float getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(float currentPrice) {
		this.currentPrice = currentPrice;
	}

	public boolean isProfit() {
		return profit;
	}

	public void setProfit(boolean profit) {
		this.profit = profit;
	}

	public float getChangePercentage() {
		return changePercentage;
	}

	public void setChangePercentage(float changePercentage) {
		this.changePercentage = changePercentage;
	}

	public float getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(float changeAmount) {
		this.changeAmount = changeAmount;
	}
}
