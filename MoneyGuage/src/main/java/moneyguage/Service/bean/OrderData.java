package moneyguage.Service.bean;

import java.util.List;

public class OrderData {
	private float availableUsdBalance;
	private float availableStockBalance;
	private String orderType;
	private float buyingPrice;
	private String buyingStock;
	private float buyingVolume;
	private float buyingTotalAmount;
	private float sellingPrice;
	private String sellingStock;
	private float sellingVolume;
	private float sellingTotalAmount;
	private boolean currency = true;
	private List<StockData> buyingOrders;
	private List<StockData> sellingOrders;

	public float getAvailableUsdBalance() {
		return availableUsdBalance;
	}

	public void setAvailableUsdBalance(float availableUsdBalance) {
		this.availableUsdBalance = availableUsdBalance;
	}

	public float getAvailableStockBalance() {
		return availableStockBalance;
	}

	public void setAvailableStockBalance(float availableStockBalance) {
		this.availableStockBalance = availableStockBalance;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public float getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(float buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public String getBuyingStock() {
		return buyingStock;
	}

	public void setBuyingStock(String buyingStock) {
		this.buyingStock = buyingStock;
	}

	public float getBuyingVolume() {
		return buyingVolume;
	}

	public void setBuyingVolume(float buyingVolume) {
		this.buyingVolume = buyingVolume;
	}

	public float getBuyingTotalAmount() {
		return buyingTotalAmount;
	}

	public void setBuyingTotalAmount(float buyingTotalAmount) {
		this.buyingTotalAmount = buyingTotalAmount;
	}

	public float getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getSellingStock() {
		return sellingStock;
	}

	public void setSellingStock(String sellingStock) {
		this.sellingStock = sellingStock;
	}

	public float getSellingVolume() {
		return sellingVolume;
	}

	public void setSellingVolume(float sellingVolume) {
		this.sellingVolume = sellingVolume;
	}

	public float getSellingTotalAmount() {
		return sellingTotalAmount;
	}

	public void setSellingTotalAmount(float sellingTotalAmount) {
		this.sellingTotalAmount = sellingTotalAmount;
	}

	public List<StockData> getBuyingOrders() {
		return buyingOrders;
	}

	public void setBuyingOrders(List<StockData> buyingOrders) {
		this.buyingOrders = buyingOrders;
	}

	public List<StockData> getSellingOrders() {
		return sellingOrders;
	}

	public void setSellingOrders(List<StockData> sellingOrders) {
		this.sellingOrders = sellingOrders;
	}

	public boolean isCurrency() {
		return currency;
	}

	public void setCurrency(boolean currency) {
		this.currency = currency;
	}
}
