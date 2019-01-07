package moneyguage.Service.bean;

import java.util.ArrayList;
import java.util.List;

public class StockConstant {
	public static final List<StockConstant> STOCK_LIST = getStocksList();
	private String tickerId;
	private String name;
	private String symbol;

	public StockConstant(String tickerId, String name, String symbol) {
		this.tickerId = tickerId;
		this.name = name;
		this.symbol = symbol;
	}

	public String getTickerId() {
		return tickerId;
	}

	public void setTickerId(String tickerId) {
		this.tickerId = tickerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public static List<StockConstant> getStocksList() {
		List<StockConstant> stockList = new ArrayList<>();
		stockList.add(new StockConstant("bitcoin", "Bitcoin", "BTC"));
		stockList.add(new StockConstant("ethereum", "Ethereum", "ETH"));
		stockList.add(new StockConstant("ripple", "Ripple", "XRP"));
		stockList.add(new StockConstant("bitcoin-cash", "Bitcoin Cash", "BCH"));
		stockList.add(new StockConstant("cardano", "Cardano", "ADA"));
		stockList.add(new StockConstant("litecoin", "Litecoin", "LTC"));
		stockList.add(new StockConstant("neo", "NEO", "NEO"));
		stockList.add(new StockConstant("stellar", "Stellar", "XLM"));
		stockList.add(new StockConstant("eos", "EOS", "EOS"));
		stockList.add(new StockConstant("iota", "IOTA", "MIOTA"));
		return stockList;
	}

	public static StockConstant getStockSymbol(String ticker) {
		for (StockConstant stockConstant : StockConstant.STOCK_LIST) {
			if (stockConstant.getTickerId().equalsIgnoreCase(ticker)) {
				return stockConstant;
			}
		}
		return null;
	}
	public static StockConstant getStockTicker(String symbol) {
		for (StockConstant stockConstant : StockConstant.STOCK_LIST) {
			if (stockConstant.getSymbol().equalsIgnoreCase(symbol)) {
				return stockConstant;
			}
		}
		return null;
	}
}
