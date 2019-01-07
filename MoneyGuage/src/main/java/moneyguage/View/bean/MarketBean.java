package moneyguage.View.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import moneyguage.Service.bean.WebStock;

@Named
@SessionScoped
public class MarketBean implements Serializable {

	private WebStock webStock = new WebStock();

	public WebStock getWebStock() {
		return webStock;
	}

	public void setWebStock(WebStock webStock) {
		this.webStock = webStock;
	}
}