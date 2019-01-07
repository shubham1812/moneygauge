package moneyguage.Model.Bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "web_user_profile")
public class DbWebUserProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cid;
	@Column(name="user_profile_id")
	private Long userProfileId;
	private Date creation_date_time;
	private String title;
	private String firstName;
	private String lastName;
	private String phoneNo;
	private String email;

	@Column(name = "user_profile_id")
	public Long getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(Long userProfileId) {
		this.userProfileId = userProfileId;
	}

/*	@Column(name = "creation_date_time")
	public Date getCreation_date_time() {
		return creation_date_time;
	}

	public void setCreation_date_time(Date creation_date_time) {
		this.creation_date_time = creation_date_time;
	}*/

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "firstname")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "lastname")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "phoneno")
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreation_date_time() {
		return creation_date_time;
	}

	public void setCreation_date_time(Date creation_date_time) {
		this.creation_date_time = creation_date_time;
	}

}
