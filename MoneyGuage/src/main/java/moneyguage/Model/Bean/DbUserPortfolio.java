package moneyguage.Model.Bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_portfolio")
public class DbUserPortfolio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Date creation_date_time;
	@Column(name = "user_profile_id")
	private Long userProfileId;
	@Column(name = "stock_name")
	private String stockName;
	private String symbol;
	private float price;
	private float volume;
	@Column(name = "total_amount")
	private float totalAmount;
	
	@Column(name = "id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(name = "creation_date_time")
	public Date getCreation_date_time() {
		return creation_date_time;
	}
	public void setCreation_date_time(Date creation_date_time) {
		this.creation_date_time = creation_date_time;
	}
	
	@Column(name = "user_profile_id")
	public Long getUserProfileId() {
		return userProfileId;
	}
	public void setUserProfileId(Long userProfileId) {
		this.userProfileId = userProfileId;
	}
	@Column(name = "stock_name")
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	@Column(name = "price")
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	@Column(name = "volume")
	public float getVolume() {
		return volume;
	}
	public void setVolume(float volume) {
		this.volume = volume;
	}
	@Column(name = "total_amount")
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	@Column(name = "symbol")
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	
}
