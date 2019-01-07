package moneyguage.View.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import moneyguage.Service.bean.StockData;
import moneyguage.Service.service.OrderService;
import moneyguage.View.bean.MarketBean;
import moneyguage.View.bean.OrderBean;
import moneyguage.View.bean.WebUserBean;

@Named
@RequestScoped
public class OrderPlaceController {
	@Inject
	OrderBean orderBean;

	@Inject
	WebUserBean webUserBean;

	@Inject
	OrderService orderService;

	@Inject
	MarketBean marketBean;

	@Inject
	MarketController marketController;

	public String order(String type) {
		orderBean.getOrderData().setOrderType(type.toUpperCase());
		if (orderBean.getOrderData().getOrderType().equalsIgnoreCase("BUY") && orderBean.getOrderData()
				.getAvailableUsdBalance() < orderBean.getOrderData().getBuyingTotalAmount()) {
			FacesContext.getCurrentInstance().addMessage("invalidRequest",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Balance Not Available", ""));
			return null;
		}
		if (orderBean.getOrderData().getOrderType().equalsIgnoreCase("SELL")
				&& orderBean.getOrderData().getAvailableStockBalance() < orderBean.getOrderData().getSellingVolume()) {
			FacesContext.getCurrentInstance().addMessage("invalidRequest",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Balance Not Available", ""));
			return null;
		}
		boolean orderPlace = false;
		if (!orderBean.getOrderData().isCurrency()) {
			orderBean.getOrderData().setSellingPrice(orderBean.getOrderData().getSellingPrice() / 70);
			orderBean.getOrderData().setBuyingPrice(orderBean.getOrderData().getBuyingPrice() / 70);
			orderBean.getOrderData().setSellingTotalAmount(orderBean.getOrderData().getSellingTotalAmount() / 70);
			orderBean.getOrderData().setBuyingTotalAmount(orderBean.getOrderData().getBuyingTotalAmount() / 70);
			orderBean.getOrderData().setAvailableUsdBalance(orderBean.getOrderData().getAvailableUsdBalance() / 70);
		}
		if (orderBean.getOrderData().getOrderType().equalsIgnoreCase("BUY")) {
			orderPlace = orderService.placeBuyingOrder(orderBean.getOrderData(), webUserBean.getUserProfileId(),
					marketBean.getWebStock().getSymbol());
		}
		if (orderBean.getOrderData().getOrderType().equalsIgnoreCase("SELL")) {
			orderPlace = orderService.placeSellingOrder(orderBean.getOrderData(), webUserBean.getUserProfileId(),
					marketBean.getWebStock().getSymbol());
		}
		orderBean.setStatus(orderPlace);
		webUserBean.setUserPortfolio(orderService.getUserPortfolio(webUserBean.getUserProfileId()));
		if (webUserBean.getUserPortfolio() != null) {
			for (StockData stock : webUserBean.getUserPortfolio().getStocks()) {
				if (stock.getStock().equalsIgnoreCase("USD")) {
					webUserBean.setBalance(stock.getTotalAmount());
				}
			}
		}
		FacesContext context = FacesContext.getCurrentInstance();
		if (orderPlace) {
			context.addMessage(null, new FacesMessage("Successful", "Your order was successfully placed "));
		} else {
			context.addMessage(null, new FacesMessage("Error", "Error in Placing your order"));
		}
		marketController.search();
		return null;
	}

	
	public void defaultCrypto() {
		marketBean.getWebStock().setSymbol("BTC");
		marketController.search();
	}

	public void availableUsdBalance() {
		orderBean.getOrderData().setBuyingTotalAmount(orderBean.getOrderData().getAvailableUsdBalance());
		orderBean.getOrderData().setBuyingVolume(
				orderBean.getOrderData().getAvailableUsdBalance() / orderBean.getOrderData().getBuyingPrice());
	}

	public void availableStockBalance() {
		orderBean.getOrderData().setSellingVolume(orderBean.getOrderData().getAvailableStockBalance());
		orderBean.getOrderData().setSellingTotalAmount(
				orderBean.getOrderData().getAvailableStockBalance() * orderBean.getOrderData().getSellingPrice());
	}

	public void sellQuantityChange() {
		orderBean.getOrderData().setSellingTotalAmount(
				orderBean.getOrderData().getSellingVolume() * orderBean.getOrderData().getSellingPrice());
	}

	public void buyQuantityChange() {
		orderBean.getOrderData().setBuyingTotalAmount(
				orderBean.getOrderData().getBuyingVolume() * orderBean.getOrderData().getBuyingPrice());
	}

	public void changeSellingValues() {
		if (orderBean.getOrderData().isCurrency()) {
			orderBean.getOrderData().setSellingPrice(orderBean.getOrderData().getSellingPrice() / 70);
			orderBean.getOrderData().setAvailableUsdBalance(orderBean.getOrderData().getAvailableUsdBalance() / 70);
		} else {
			orderBean.getOrderData().setAvailableUsdBalance(orderBean.getOrderData().getAvailableUsdBalance() * 70);
			orderBean.getOrderData().setSellingPrice(orderBean.getOrderData().getSellingPrice() * 70);
		}
		sellQuantityChange();
	}

	public void changeBuyingValues() {
		if (orderBean.getOrderData().isCurrency()) {
			orderBean.getOrderData().setBuyingPrice(orderBean.getOrderData().getBuyingPrice() / 70);
			orderBean.getOrderData().setAvailableUsdBalance(orderBean.getOrderData().getAvailableUsdBalance() / 70);
		} else {
			orderBean.getOrderData().setAvailableUsdBalance(orderBean.getOrderData().getAvailableUsdBalance() * 70);
			orderBean.getOrderData().setBuyingPrice(orderBean.getOrderData().getBuyingPrice() * 70);
		}
		buyQuantityChange();
	}
}
