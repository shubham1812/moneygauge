package moneyguage.View.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import moneyguage.Service.bean.OrderData;
import moneyguage.Service.bean.StockConstant;
import moneyguage.Service.bean.StockData;
import moneyguage.Service.bean.UserPortfolio;
import moneyguage.Service.bean.WebStock;
import moneyguage.Service.service.MarketService;
import moneyguage.Service.service.OrderService;
import moneyguage.View.bean.CurrentMarketBean;
import moneyguage.View.bean.DemoBean;
import moneyguage.View.bean.MarketBean;
import moneyguage.View.bean.OrderBean;
import moneyguage.View.bean.RegistrationBean;
import moneyguage.View.bean.WebUserBean;

@Named
@RequestScoped
public class MarketController {

	@Inject
	MarketBean marketBean;
	@Inject
	RegistrationBean registrationBean;
	@Inject
	MarketService marketService;
	@Inject
	OrderBean orderBean;
	@Inject
	WebUserBean webUserBean;
	@Inject
	OrderService orderService;
	@Inject
	CurrentMarketBean currentMarketBean;
	@Inject
	OrderPlaceController orderPlaceController;
	@Inject
	DemoBean demoBean;

	public void searchStock(String symbol) {
		WebStock webStock = new WebStock();
		webStock.setSymbol(symbol);
		marketBean.setWebStock(webStock);
		search();
	}

	public String search() {
		marketBean.setWebStock(marketService.findStockDetails(marketBean.getWebStock(), 10));
		if (webUserBean.isValid()) {
			OrderData orderData = new OrderData();
			UserPortfolio userPortfolio = webUserBean.getUserPortfolio();
			for (StockData stockData : userPortfolio.getStocks()) {
				if (stockData.getSymbol().equalsIgnoreCase(marketBean.getWebStock().getSymbol())) {
					orderData.setAvailableStockBalance(stockData.getVolume());
				}
				if (stockData.getStock().equalsIgnoreCase("USD")) {
					orderData.setAvailableUsdBalance(stockData.getTotalAmount());
				}
			}
			orderData.setBuyingStock(marketBean.getWebStock().getTickerName());
			orderData.setSellingStock(marketBean.getWebStock().getTickerName());
			orderData.setBuyingPrice(Float.valueOf(marketBean.getWebStock().getCurrentPrice()));
			orderData.setSellingPrice((Float.valueOf(marketBean.getWebStock().getCurrentPrice())));
			OrderData orderData1 = orderService.getStockOrders(marketBean.getWebStock().getSymbol());
			orderData.setBuyingOrders(orderData1.getBuyingOrders());
			orderData.setSellingOrders(orderData1.getSellingOrders());
			orderBean.setOrderData(orderData);
		}
		return null;
	}

	public List<String> getCryptoList() {
		List<StockConstant> stockConstants = StockConstant.STOCK_LIST;
		List<String> stock = new ArrayList<>();
		for (StockConstant stockConstant : stockConstants) {
			stock.add(stockConstant.getTickerId());
		}
		return stock;
	}

	public void setCurrentMarketBean(String page) {
		List<WebStock> webStocks = marketService.getCurrentMarketValues();
		currentMarketBean.setWebStock(webStocks);
		if (page.equalsIgnoreCase("cryptos")) {
			orderPlaceController.defaultCrypto();
		} else if (page.equalsIgnoreCase("home")) {
			demoBean.setRandomNumber(getRandomNumberInRange(1, 3));
		}
	}

	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}