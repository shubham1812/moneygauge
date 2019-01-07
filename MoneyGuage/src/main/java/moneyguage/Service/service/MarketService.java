package moneyguage.Service.service;

import java.util.ArrayList;
import java.util.List;

import moneyguage.Model.Bean.DbMarket;
import moneyguage.Model.Repository.Market;
import moneyguage.Service.bean.StockConstant;
import moneyguage.Service.bean.WebStock;

public class MarketService {

	public WebStock findStockDetails(WebStock webStock, int numberOfValues) {
		String symbol = webStock.getSymbol();
		String ticker = StockConstant.getStockTicker(symbol).getTickerId();
		List<DbMarket> stockList = Market.getStockData(symbol, numberOfValues);

		webStock.setPriceUsd(new ArrayList<String>());
		webStock.setLastUpdated(new ArrayList<String>());
		if (stockList != null) {
			for (DbMarket stock : stockList) {
				webStock.getPriceUsd().add(String.valueOf(stock.getPrice_usd()));
				webStock.getLastUpdated().add(String.valueOf(stock.getLast_updated()));
			}
			webStock.setCurrentPrice(String.valueOf(stockList.get(0).getPrice_usd()));
			webStock.setSymbol(stockList.get(0).getSymbol());
			webStock.setTickerId(ticker);
			webStock.setTickerName(StockConstant.getStockTicker(symbol).getName());
			if (numberOfValues == 100) {
				webStock.setHrChange(
						(Float.valueOf(webStock.getPriceUsd().get(0)) - Float.valueOf(webStock.getPriceUsd().get(20)))
								* 100 / Float.valueOf(webStock.getPriceUsd().get(0)));
				webStock.setDayChange(
						(Float.valueOf(webStock.getPriceUsd().get(0)) - Float.valueOf(webStock.getPriceUsd().get(99)))
								* 100 / Float.valueOf(webStock.getPriceUsd().get(0)));
			}
		}
		return webStock;
	}

	public List<String> getCryptoList() {
		List<String> cryptoList = Market.getDistinctStocks();
		return cryptoList;
	}

	public List<String> findAllPrice(WebStock webStock) {
		String search = webStock.getStock();
		List<DbMarket> stockList = Market.getStockData(search, 10);

		System.out.println(stockList);
		List<String> priceUsd = new ArrayList<>();
		for (DbMarket stock : stockList) {
			priceUsd.add(String.valueOf(stock.getPrice_usd()));
		}
		return priceUsd;
	}

	public List<String> findAllDates(WebStock webStock) {
		String search = webStock.getStock();
		List<DbMarket> stockList = Market.getStockData(search, 10);
		System.out.println(stockList);
		List<String> lastUpdated = new ArrayList<>();
		for (DbMarket stock : stockList) {
			lastUpdated.add(String.valueOf(stock.getLast_updated()));
		}
		return lastUpdated;
	}

	public List<WebStock> getCurrentMarketValues() {
		List<StockConstant> stocks = StockConstant.STOCK_LIST;
		List<WebStock> webStocks = new ArrayList<>();
		for (StockConstant stock : stocks) {
			WebStock w = new WebStock();
			w.setSymbol(stock.getSymbol());
			webStocks.add(findStockDetails(w, 100));
		}
		return webStocks;
	}

}