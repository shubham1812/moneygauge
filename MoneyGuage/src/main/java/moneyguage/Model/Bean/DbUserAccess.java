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
@Table(name = "user_access")
public class DbUserAccess implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cid;
	@Column(name="user_profile_id")
	private Long userProfileId;
	private String username;
	private String password;
	private String email;
	private Date creation_date_time;
	private String otp;

	public DbUserAccess() {
	}

	public DbUserAccess(String username, String password, String email) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	@Column(name = "cid")
	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Column(name = "user_profile_id")
	public Long getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(Long userProfileId) {
		this.userProfileId = userProfileId;
	}

	@Column(name = "creation_date_time")
	public Date getCreation_date_time() {
		return creation_date_time;
	}

	public void setCreation_date_time(Date creation_date_time) {
		this.creation_date_time = creation_date_time;
	}
	@Column(name = "otp")
	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
}