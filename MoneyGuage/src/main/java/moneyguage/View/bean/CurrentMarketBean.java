package moneyguage.View.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import moneyguage.Service.bean.WebStock;

@Named
@SessionScoped
public class CurrentMarketBean implements Serializable {
	private List<WebStock> webStock;
	private String search;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public List<WebStock> getWebStock() {
		return webStock;
	}

	public void setWebStock(List<WebStock> webStock) {
		this.webStock = webStock;
	}

}
