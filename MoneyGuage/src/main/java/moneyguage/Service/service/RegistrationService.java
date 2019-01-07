package moneyguage.Service.service;

import java.io.IOException;
import java.util.Random;

import moneyguage.Model.Bean.DbUserAccess;
import moneyguage.Model.Bean.DbWebUserProfile;
import moneyguage.Model.Repository.UserAuthentication;
import moneyguage.Service.Util.EmailUtil;
import moneyguage.Service.Util.PasswordEncryption;
import moneyguage.Service.Util.UniqueIdGenerator;
import moneyguage.Service.bean.WebUserProfile;

public class RegistrationService {

	public boolean checkIfUserExists(String emailId) {
		if (emailId != null) {
			if (UserAuthentication.isUserExists(emailId)) {
				return true;
			}
		}
		return false;
	}

	public String saveUser(WebUserProfile userProfile, String password) {
		DbWebUserProfile dbWebUserProfile = new DbWebUserProfile();
		Long id = UniqueIdGenerator.generateUniqueId();
		dbWebUserProfile.setUserProfileId(id);
		dbWebUserProfile.setEmail(userProfile.getEmailId());
		dbWebUserProfile.setTitle(userProfile.getTitle());
		dbWebUserProfile.setFirstName(userProfile.getFirstName());
		dbWebUserProfile.setLastName(userProfile.getLastName());
		dbWebUserProfile.setPhoneNo(userProfile.getPhoneNo());

		DbUserAccess dbUserAccess = new DbUserAccess();
		dbUserAccess.setEmail(userProfile.getEmailId());
		dbUserAccess.setPassword(PasswordEncryption.generateSecurePassword(password, "portfolio"));
		dbUserAccess.setUsername(userProfile.getEmailId());
		dbUserAccess.setUserProfileId(id);
		Random rand = new Random();
		String otp = String.format("%04d%n", rand.nextInt(10000));
		dbUserAccess.setOtp(otp);
		try {
			if (UserAuthentication.register(dbWebUserProfile, dbUserAccess)) {
				sendEmail(dbUserAccess);
				return dbUserAccess.getUserProfileId().toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void sendEmail(DbUserAccess dbUserAccess) {
		try {
			EmailUtil.sendEmail(dbUserAccess.getEmail(), "Successful Registration",
					"You are successfully regsitered on moneygauge. Please use " + dbUserAccess.getOtp()
							+ " as 2FA Authentication Code");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String checkIfOtpTrue(WebUserProfile userProfile, String otp) {
		DbUserAccess user = UserAuthentication.getUserByUserId(userProfile.getEmailId());
		if (user != null && user.getUsername().equals(userProfile.getEmailId()) && otp.equals(user.getOtp().replace("\n",""))) {
			return user.getUserProfileId().toString();
		} else {
			return null;
		}
	}
}
