package moneyguage.Model.Bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_permissions")
public class DbUserPermission implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "user_profile_id")
	private String userProfileId;
	private String admin;
	@Column(name = "trading_option")
	private String tradingOption;
	@Column(name = "buying_option")
	private String buyingOption;
	@Column(name = "selling_option")
	private String sellingOption;

	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "user_profile_id")
	public String getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(String userProfileId) {
		this.userProfileId = userProfileId;
	}

	@Column(name = "trading_option")
	public String getTradingOptions() {
		return tradingOption;
	}

	public void setTradingOption(String tradingOptions) {
		this.tradingOption = tradingOptions;
	}

	@Column(name = "buying_option")
	public String getBuyingOption() {
		return buyingOption;
	}

	public void setBuyingOption(String buyingOption) {
		this.buyingOption = buyingOption;
	}

	@Column(name = "selling_option")
	public String getSellingOption() {
		return sellingOption;
	}

	public void setSellingOption(String sellingOption) {
		this.sellingOption = sellingOption;
	}

	@Column(name = "admin")
	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}
}
