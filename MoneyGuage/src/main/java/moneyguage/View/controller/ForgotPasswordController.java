package moneyguage.View.controller;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import moneyguage.Service.service.ForgotPasswordService;
import moneyguage.Service.service.RegistrationService;
import moneyguage.View.bean.ForgotPasswordBean;

@Named
@RequestScoped
public class ForgotPasswordController {

	@Inject
	ForgotPasswordBean forgotPasswordBean;

	@Inject
	RegistrationService registrationService;

	@Inject
	ForgotPasswordService forgotPasswordService;

	@Inject
	SigninController signinController;

	public String generateOtp() {
		String redirect = null;
		if (registrationService.checkIfUserExists(forgotPasswordBean.getForgotPasswordEmail())) {

			boolean isOtpGenerated = forgotPasswordService.generateOtp(forgotPasswordBean.getForgotPasswordEmail());
			String message = isOtpGenerated ? "An OTP has been successfully sent to your Email Id."
					: "Please try later";
			forgotPasswordBean.setOtpGenerated(true);
			FacesContext.getCurrentInstance().addMessage("forgotPasswordEmail", new FacesMessage(message));
		} else {
			FacesContext.getCurrentInstance().addMessage("forgotPasswordEmail",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email Id does not exist.", ""));
		}
		return redirect;
	}

	public String resetPasswordOtp() {
		String redirect = null;
		boolean isPasswordUpdated = forgotPasswordService.updatePassword(forgotPasswordBean.getForgotPasswordEmail(),
				forgotPasswordBean.getOtp(), forgotPasswordBean.getNewPassword());
		if (isPasswordUpdated) {
			redirect = signinController.loginExistingUser(forgotPasswordBean.getForgotPasswordEmail(),
					forgotPasswordBean.getNewPassword());
			forgotPasswordBean.reset();
		} else {
			FacesContext.getCurrentInstance().addMessage("invalidIdPassword", new FacesMessage("Invalid Otp"));
		}
		return redirect;
	}

}
