package jp.co.aforce.api;


public class SendEmail {

	public static void main(String[] args) {
		String to = "r.takahashi.aforce@gmail.com";
		String subject = "test";
		String body = "testmessage";

		try {
			GmailApiUtil.sendEmail(to, subject, body);
			System.out.println("Email sent successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to send email: " + e.getMessage());
		}
	}

}