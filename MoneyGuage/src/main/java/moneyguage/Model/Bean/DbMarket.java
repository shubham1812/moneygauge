package moneyguage.Model.Bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ticker")
public class DbMarket implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date last_updated;
	private String price_usd;
	private String symbol;
	private String ticker_id;
	private String ticker_name;

	@Column(name = "id")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "last_updated")
	public Date getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(Date last_updated) {
		this.last_updated = last_updated;
	}

	@Column(name = "price_usd")
	public String getPrice_usd() {
		return price_usd;
	}

	public void setPrice_usd(String price_usd) {
		this.price_usd = price_usd;
	}

	@Column(name = "symbol")
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Column(name = "ticker_id")
	public String getTicker_id() {
		return ticker_id;
	}

	public void setTicker_id(String ticker_id) {
		this.ticker_id = ticker_id;
	}

	@Column(name = "ticker_name")
	public String getTicker_name() {
		return ticker_name;
	}

	public void setTicker_name(String ticker_name) {
		this.ticker_name = ticker_name;
	}
}