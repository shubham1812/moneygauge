package moneyguage.Service.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;

import moneyguage.Model.Bean.DbPortfolioOrders;
import moneyguage.Model.Bean.DbUserPortfolio;
import moneyguage.Model.Repository.Market;
import moneyguage.Model.Repository.MarketRepository;
import moneyguage.Service.Util.UniqueIdGenerator;
import moneyguage.Service.bean.OrderData;
import moneyguage.Service.bean.StockData;
import moneyguage.Service.bean.UserPortfolio;
import moneyguage.Service.bean.WebStock;

public class OrderService {

	public UserPortfolio getStockInPortfolio(String userProfileId, String stockName) {
		List<DbUserPortfolio> dbPortfolioOrders = MarketRepository.getUserPortfolio(userProfileId, stockName);
		UserPortfolio userPortfolio = getUserPortfolio(userProfileId, dbPortfolioOrders);
		return userPortfolio;
	}

	public UserPortfolio getUserPortfolio(String userProfileId) {
		List<DbUserPortfolio> dbPortfolioOrders = MarketRepository.getUserPortfolio(userProfileId, null);
		UserPortfolio userPortfolio = getUserPortfolio(userProfileId, dbPortfolioOrders);
		return userPortfolio;
	}

	public UserPortfolio getUserPortfolio(String userProfileId, List<DbUserPortfolio> dbPortfolioOrders) {
		UserPortfolio userPortfolio = new UserPortfolio();
		userPortfolio.setUserProfileId(userProfileId);
		userPortfolio.setStocks(new ArrayList<>());
		for (DbUserPortfolio dbPortfolioOrder : dbPortfolioOrders) {
			StockData stock = new StockData();
			stock.setCreationDateTime(dbPortfolioOrder.getCreation_date_time());
			stock.setPrice(dbPortfolioOrder.getPrice());
			stock.setTotalAmount(dbPortfolioOrder.getTotalAmount());
			stock.setVolume(dbPortfolioOrder.getVolume());
			stock.setStock(dbPortfolioOrder.getStockName());
			stock.setSymbol(dbPortfolioOrder.getSymbol());
			if (!stock.getSymbol().equalsIgnoreCase("USD")) {
				stock.setCurrentPrice(
						Float.valueOf(Market.getCurrentPrice(dbPortfolioOrder.getSymbol()).getPrice_usd()));
				if (stock.getCurrentPrice() >= stock.getPrice()) {
					stock.setProfit(true);
					stock.setChangeAmount((stock.getCurrentPrice() - stock.getPrice()) * stock.getVolume());
					stock.setChangePercentage(stock.getChangeAmount() * 100 / stock.getPrice());
				} else {
					stock.setProfit(false);
					stock.setChangeAmount((stock.getPrice() - stock.getCurrentPrice()) * stock.getVolume());
					stock.setChangePercentage((stock.getChangeAmount() * 100) / stock.getPrice());
				}
			} else {
				stock.setPrice(dbPortfolioOrder.getTotalAmount());
				stock.setCurrentPrice(dbPortfolioOrder.getTotalAmount());
			}
			userPortfolio.getStocks().add(stock);
		}
		return userPortfolio;
	}

	public boolean placeBuyingOrder(OrderData orderData, String userProfileId, String symbol) {
		OrderService orderService = new OrderService();

		UserPortfolio currentPortfolio = orderService.getStockInPortfolio(userProfileId, orderData.getBuyingStock());
		List<StockData> currentStockData = currentPortfolio.getStocks();
		float balance = 0;
		float stockPrice = 0;
		float stockVolume = 0;
		float stockTotalAmount = 0;
		for (StockData currentStock : currentStockData) {
			if (currentStock.getStock().equalsIgnoreCase("USD")) {
				balance = currentStock.getTotalAmount();
			} else if (currentStock.getStock().equalsIgnoreCase(orderData.getBuyingStock())) {
				stockVolume = currentStock.getVolume();
				stockPrice = currentStock.getPrice();
				stockTotalAmount = currentStock.getTotalAmount();
			}
		}
		long orderId = UniqueIdGenerator.generateUniqueId();
		DbPortfolioOrders dbStockOrder = new DbPortfolioOrders();
		dbStockOrder.setOrderId(orderId);
		dbStockOrder.setPrice(orderData.getBuyingPrice());
		dbStockOrder.setStockName(orderData.getBuyingStock());
		dbStockOrder.setTotalAmount(orderData.getBuyingTotalAmount());
		dbStockOrder.setUserProfileId(Long.valueOf(userProfileId));
		dbStockOrder.setVolume(orderData.getBuyingVolume());
		dbStockOrder.setSymbol(symbol);

		DbPortfolioOrders dbStockDataPrice = new DbPortfolioOrders();
		dbStockDataPrice.setOrderId(orderId);
		dbStockDataPrice.setOrderType("BAL");
		dbStockDataPrice.setPrice(0);
		dbStockDataPrice.setStockName("USD");
		dbStockDataPrice.setVolume(0);
		dbStockDataPrice.setUserProfileId(Long.valueOf(userProfileId));
		dbStockDataPrice.setSymbol("USD");

		DbUserPortfolio dbUserPortfolio = new DbUserPortfolio();
		dbUserPortfolio.setStockName(orderData.getBuyingStock());
		dbUserPortfolio.setUserProfileId(Long.valueOf(userProfileId));
		dbUserPortfolio.setSymbol(symbol);

		DbUserPortfolio dbUsdPrice = new DbUserPortfolio();
		dbUsdPrice.setPrice(0);
		dbUsdPrice.setStockName("USD");
		dbUsdPrice.setUserProfileId(Long.valueOf(userProfileId));
		dbUsdPrice.setVolume(0);
		dbUsdPrice.setSymbol("USD");

		dbStockOrder.setOrderType("BUY");
		dbStockDataPrice.setTotalAmount(balance - orderData.getBuyingTotalAmount());
		dbUserPortfolio
				.setPrice(((orderData.getBuyingPrice() * orderData.getBuyingVolume()) + (stockPrice * stockVolume))
						/ (orderData.getBuyingVolume() + stockVolume));
		dbUserPortfolio.setTotalAmount(orderData.getBuyingTotalAmount() + stockTotalAmount);
		dbUserPortfolio.setVolume(stockVolume + orderData.getBuyingVolume());
		dbUsdPrice.setTotalAmount(balance - orderData.getBuyingTotalAmount());

		boolean status = false;
		try {
			status = MarketRepository.placeOrder(dbStockOrder, dbStockDataPrice, dbUsdPrice, dbUserPortfolio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public boolean placeSellingOrder(OrderData orderData, String userProfileId, String symbol) {
		OrderService orderService = new OrderService();

		UserPortfolio currentPortfolio = orderService.getStockInPortfolio(userProfileId, orderData.getSellingStock());
		List<StockData> currentStockData = currentPortfolio.getStocks();
		float balance = 0;
		float stockPrice = 0;
		float stockVolume = 0;
		float stockTotalAmount = 0;
		for (StockData currentStock : currentStockData) {
			if (currentStock.getStock().equalsIgnoreCase("USD")) {
				balance = currentStock.getTotalAmount();
			} else if (currentStock.getStock().equalsIgnoreCase(orderData.getSellingStock())) {
				stockVolume = currentStock.getVolume();
				stockPrice = currentStock.getPrice();
				stockTotalAmount = currentStock.getTotalAmount();
			}
		}
		long orderId = UniqueIdGenerator.generateUniqueId();
		DbPortfolioOrders dbStockOrder = new DbPortfolioOrders();
		dbStockOrder.setOrderId(orderId);
		dbStockOrder.setPrice(orderData.getSellingPrice());
		dbStockOrder.setStockName(orderData.getSellingStock());
		dbStockOrder.setTotalAmount(orderData.getSellingTotalAmount());
		dbStockOrder.setUserProfileId(Long.valueOf(userProfileId));
		dbStockOrder.setVolume(orderData.getSellingVolume());
		dbStockOrder.setSymbol(symbol);

		DbPortfolioOrders dbStockDataPrice = new DbPortfolioOrders();
		dbStockDataPrice.setOrderId(orderId);
		dbStockDataPrice.setOrderType("BAL");
		dbStockDataPrice.setPrice(0);
		dbStockDataPrice.setStockName("USD");
		dbStockDataPrice.setVolume(0);
		dbStockDataPrice.setUserProfileId(Long.valueOf(userProfileId));
		dbStockDataPrice.setSymbol("USD");

		DbUserPortfolio dbUserPortfolio = new DbUserPortfolio();
		dbUserPortfolio.setStockName(orderData.getSellingStock());
		dbUserPortfolio.setUserProfileId(Long.valueOf(userProfileId));
		dbUserPortfolio.setSymbol(symbol);

		DbUserPortfolio dbUsdPrice = new DbUserPortfolio();
		dbUsdPrice.setPrice(0);
		dbUsdPrice.setStockName("USD");
		dbUsdPrice.setUserProfileId(Long.valueOf(userProfileId));
		dbUsdPrice.setVolume(0);
		dbUsdPrice.setSymbol("USD");

		dbStockOrder.setOrderType("SELL");
		dbStockDataPrice.setTotalAmount(balance + orderData.getSellingTotalAmount());
		dbUserPortfolio.setPrice(stockPrice);
		dbUserPortfolio.setVolume(stockVolume - orderData.getSellingVolume());
		dbUserPortfolio.setTotalAmount(stockTotalAmount - orderData.getSellingTotalAmount());
		dbUsdPrice.setTotalAmount(balance + orderData.getSellingTotalAmount());

		boolean status = false;
		try {
			status = MarketRepository.placeOrder(dbStockOrder, dbStockDataPrice, dbUsdPrice, dbUserPortfolio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public OrderData getOrderData(String stock, String userProfileId) {
		OrderData orderData = new OrderData();
		UserPortfolio userPortfolio = getUserPortfolio(userProfileId);
		for (StockData stockData : userPortfolio.getStocks()) {
			if (stockData.getStock().equalsIgnoreCase(stock)) {
				orderData.setAvailableStockBalance(stockData.getVolume());
			}
			if (stockData.getStock().equalsIgnoreCase("USD")) {
				orderData.setAvailableUsdBalance(stockData.getTotalAmount());
			}
		}
		return orderData;
	}

	public List<StockData> getOrders(String userProfileId) {
		List<DbPortfolioOrders> dbPortfolioOrders = MarketRepository.getUserOrders(Long.valueOf(userProfileId));
		List<StockData> stocks = new ArrayList<>();
		for (DbPortfolioOrders dbPortfolioOrder : dbPortfolioOrders) {
			StockData st = new StockData();
			st.setCreationDateTime(dbPortfolioOrder.getCreation_date_time());
			st.setOrderId(String.valueOf(dbPortfolioOrder.getOrderId()));
			st.setOrderType(dbPortfolioOrder.getOrderType());
			st.setPrice(dbPortfolioOrder.getPrice());
			st.setStock(dbPortfolioOrder.getStockName());
			st.setSymbol(dbPortfolioOrder.getSymbol());
			st.setTotalAmount(dbPortfolioOrder.getTotalAmount());
			st.setVolume(dbPortfolioOrder.getVolume());
			stocks.add(st);
		}
		return stocks;
	}

	public OrderData getStockOrders(String symbol) {
		List<StockData> buyData = new ArrayList<>();
		List<StockData> sellData = new ArrayList<>();
		List<DbPortfolioOrders> dbPortfolioOrders = MarketRepository.getStockOrders(symbol);
		for (DbPortfolioOrders dbPortfolioOrder : dbPortfolioOrders) {
			StockData stockData = new StockData();
			if (dbPortfolioOrder.getOrderType().equalsIgnoreCase("BUY")) {
				stockData.setPrice(dbPortfolioOrder.getPrice());
				stockData.setVolume(dbPortfolioOrder.getVolume());
				stockData.setTotalAmount(dbPortfolioOrder.getTotalAmount());
				buyData.add(stockData);
			} else if (dbPortfolioOrder.getOrderType().equalsIgnoreCase("SELL")) {
				stockData.setPrice(dbPortfolioOrder.getPrice());
				stockData.setVolume(dbPortfolioOrder.getVolume());
				stockData.setTotalAmount(dbPortfolioOrder.getTotalAmount());
				sellData.add(stockData);
			}
		}
		OrderData orderData = new OrderData();
		orderData.setBuyingOrders(buyData);
		orderData.setSellingOrders(sellData);
		return orderData;
	}

	public boolean addMoney(String userProfileId, float buyingTotalAmount) {
		List<DbUserPortfolio> dbUserPortfolio = MarketRepository.getUserPortfolio(userProfileId, null);
		DbUserPortfolio user = new DbUserPortfolio();
		for (DbUserPortfolio userPortfolio : dbUserPortfolio) {
			if (userPortfolio.getSymbol().equalsIgnoreCase("USD")) {
				user.setId(userPortfolio.getId());
				user.setPrice(0);
				user.setStockName("USD");
				user.setSymbol("USD");
				user.setTotalAmount(userPortfolio.getTotalAmount() + buyingTotalAmount);
				user.setUserProfileId(userPortfolio.getUserProfileId());
				user.setVolume(0);
			}
		}
		DbPortfolioOrders dbPortfolioOrders = new DbPortfolioOrders();
		dbPortfolioOrders.setOrderId(UniqueIdGenerator.generateUniqueId());
		dbPortfolioOrders.setOrderType("BAL");
		dbPortfolioOrders.setPrice(0);
		dbPortfolioOrders.setStockName("USD");
		dbPortfolioOrders.setSymbol("USD");
		dbPortfolioOrders.setTotalAmount(user.getTotalAmount());
		dbPortfolioOrders.setUserProfileId(Long.valueOf(userProfileId));
		dbPortfolioOrders.setVolume(0);
		boolean status = false;
		try {
			status = MarketRepository.addMoney(user, dbPortfolioOrders);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;

	}
}
