package moneyguage.Service.Util;

import java.io.IOException;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

public class EmailUtil {

	public static void sendEmail(String toEmail, String subject, String body) throws IOException {
		Email from = new Email("support@moneygauge.com");
		Email to = new Email(toEmail);
		Content content = new Content("text/plain", body);
		Mail mail = new Mail(from, subject, to, content);

		SendGrid sg = new SendGrid("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");
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
		} catch (IOException ex) {
			throw ex;
		}
	}

	public static void main(String[] args) throws IOException {
		Email from = new Email("support@moneygauge.com");
		String subject = "Sending First Mail";
		Email to = new Email("sgodshalwar@gmail.com");
		Content content = new Content("text/plain", "your Otp is : 2222");
		Mail mail = new Mail(from, subject, to, content);

		SendGrid sg = new SendGrid("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");
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
		} catch (IOException ex) {
			throw ex;
		}
	}

}