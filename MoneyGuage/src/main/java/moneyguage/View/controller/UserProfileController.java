package moneyguage.View.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.api.UITabPanel;
import org.primefaces.event.TabChangeEvent;

import moneyguage.Service.bean.StockData;
import moneyguage.Service.bean.WebUserProfile;
import moneyguage.Service.service.OrderService;
import moneyguage.Service.service.SigninService;
import moneyguage.View.bean.UserProfileBean;
import moneyguage.View.bean.UserProfileWidgetBean;
import moneyguage.View.bean.WebUserBean;

@Named
@RequestScoped
public class UserProfileController {
	@Inject
	WebUserBean webUserBean;
	@Inject
	SigninService signinService;
	@Inject
	UserProfileBean userProfileBean;
	@Inject
	UserProfileWidgetBean userProfileWidgetBean;
	@Inject
	OrderService orderService;

	public void getUserProfile() {
		WebUserProfile webUserProfile = signinService.getWebUserDetails(webUserBean.getUserProfileId());
		userProfileBean.setEmail(webUserProfile.getEmailId());
		userProfileBean.setName(
				webUserProfile.getTitle() + " " + webUserProfile.getFirstName() + " " + webUserProfile.getLastName());
		userProfileBean.setPhoneNo(webUserProfile.getPhoneNo());
		userProfileBean.setOrders(orderService.getOrders(webUserBean.getUserProfileId()));
		float totalNetWorth = 0;
		for (StockData stockData : webUserBean.getUserPortfolio().getStocks()) {
			if (stockData.getSymbol().equalsIgnoreCase("USD")) {
				totalNetWorth += stockData.getTotalAmount();
			} else {
				totalNetWorth += stockData.getCurrentPrice() * stockData.getVolume();
			}
		}
		userProfileBean.setNetWorth(totalNetWorth);
	}

	public void onkTabChange(TabChangeEvent event) {
		UITabPanel tabView = (UITabPanel) event.getComponent();
		int activeTab = tabView.getChildren().indexOf(event.getTab());
		userProfileWidgetBean.setActiveIndex(activeTab);
	}
}
