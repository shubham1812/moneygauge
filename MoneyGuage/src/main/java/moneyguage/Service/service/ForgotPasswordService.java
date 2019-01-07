package moneyguage.Service.service;

import moneyguage.Model.Bean.DbUserAccess;
import moneyguage.Model.Repository.UserAuthentication;
import moneyguage.Service.Util.EmailUtil;
import moneyguage.Service.Util.PasswordEncryption;

public class ForgotPasswordService {

	public boolean generateOtp(String forgotPasswordEmail) {

		int randomPin = (int) (Math.random() * 9000) + 1000;
		String otp = String.valueOf(randomPin);
		String message = "Your OTP is : " + otp;
		try {
			EmailUtil.sendEmail(forgotPasswordEmail, "MoneyGauge Forgot Password", message);
			UserAuthentication.resetOtp(forgotPasswordEmail, otp);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updatePassword(String forgotPasswordEmail, String otp, String newPassword) {

		DbUserAccess user = UserAuthentication.getUserByUserId(forgotPasswordEmail);
		if (user != null && user.getUsername().equals(forgotPasswordEmail) && otp.equals(user.getOtp())) {
			try {
				UserAuthentication.resetPassword(forgotPasswordEmail,
						PasswordEncryption.generateSecurePassword(newPassword, "portfolio"));
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
