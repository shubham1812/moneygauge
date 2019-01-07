package moneyguage.Service.bean;

public class WebUserProfile {

	private String title;
	private String firstName;
	private String lastName;
	private String emailId;
	private String phoneNo;
	private String registrationSource;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRegistrationSource() {
		return registrationSource;
	}
	public void setRegistrationSource(String registrationSource) {
		this.registrationSource = registrationSource;
	}
}
