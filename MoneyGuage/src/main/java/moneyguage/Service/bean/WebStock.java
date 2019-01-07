package moneyguage.Service.bean;

import java.util.List;

public class WebStock {

	private String stock;
	private List<String> priceUsd;
	private List<String> lastUpdated;
	private String symbol;
	private String tickerId;
	private String tickerName;
	private String currentPrice;
	private float hrChange;
	private float dayChange;
	
	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public List<String> getPriceUsd() {
		return priceUsd;
	}

	public void setPriceUsd(List<String> priceUsd) {
		this.priceUsd = priceUsd;
	}

	public List<String> getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(List<String> lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getTickerId() {
		return tickerId;
	}

	public void setTickerId(String tickerId) {
		this.tickerId = tickerId;
	}

	public String getTickerName() {
		return tickerName;
	}

	public void setTickerName(String tickerName) {
		this.tickerName = tickerName;
	}

	public String getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(String string) {
		this.currentPrice = string;
	}

	public float getHrChange() {
		return hrChange;
	}

	public void setHrChange(float hrChange) {
		this.hrChange = hrChange;
	}

	public float getDayChange() {
		return dayChange;
	}

	public void setDayChange(float dayChange) {
		this.dayChange = dayChange;
	}

}