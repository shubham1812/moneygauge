package moneyguage.View.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named
@SessionScoped
public class ForgotPasswordBean implements Serializable {

	private String forgotPasswordEmail;
	private boolean isOtpGenerated = false;
	private String otp;
	private String newPassword;

	public String getForgotPasswordEmail() {
		return forgotPasswordEmail;
	}

	public void setForgotPasswordEmail(String forgotPasswordEmail) {
		this.forgotPasswordEmail = forgotPasswordEmail;
	}

	public boolean isOtpGenerated() {
		return isOtpGenerated;
	}

	public void setOtpGenerated(boolean isOtpGenerated) {
		this.isOtpGenerated = isOtpGenerated;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public void reset() {
		this.forgotPasswordEmail = null;
		this.otp = null;
		this.newPassword = null;
	}

}
