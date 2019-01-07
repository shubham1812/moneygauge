package moneyguage.Service.service;

import moneyguage.Model.Bean.DbUserAccess;
import moneyguage.Model.Bean.DbUserPermission;
import moneyguage.Model.Bean.DbWebUserProfile;
import moneyguage.Model.Repository.PermissionRepository;
import moneyguage.Model.Repository.UserAuthentication;
import moneyguage.Service.Util.PasswordEncryption;
import moneyguage.Service.bean.Permissions;
import moneyguage.Service.bean.WebUserProfile;

public class SigninService {

	public String signinUser(String username, String password) {
		DbUserAccess user = UserAuthentication.getUserByUserId(username);
		if (user != null && user.getUsername().equals(username)
				&& PasswordEncryption.verifyUserPassword(password, user.getPassword(), "portfolio")) {
			return user.getUserProfileId().toString();
		} else {
			return null;
		}
	}

	public String signinUser(String username) {
		DbUserAccess user = UserAuthentication.getUserByUserId(username);
		if (user != null && user.getUsername().equals(username)) {
			return user.getUserProfileId().toString();
		} else {
			return null;
		}
	}

	public WebUserProfile getWebUserDetails(String userProfileId) {
		if (userProfileId == null) {
			return null;
		}
		DbWebUserProfile dbWebUserProfile = UserAuthentication
				.getWebUserDetailsByUserProfileId(Long.valueOf(userProfileId));
		if (dbWebUserProfile == null) {
			return null;
		}
		WebUserProfile webUserProfile = new WebUserProfile();
		webUserProfile.setEmailId(dbWebUserProfile.getEmail());
		webUserProfile.setFirstName(dbWebUserProfile.getFirstName());
		webUserProfile.setLastName(dbWebUserProfile.getLastName());
		webUserProfile.setPhoneNo(dbWebUserProfile.getPhoneNo());
		webUserProfile.setTitle(dbWebUserProfile.getTitle());
		return webUserProfile;
	}

	public String getUserProfileId(String username) {
		DbUserAccess user = UserAuthentication.getUserByUserId(username);
		if (user != null) {
			return user.getUserProfileId().toString();
		}
		return null;
	}

}
