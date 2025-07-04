package jp.co.aforce.api;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Collections;
import java.util.Properties;

import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;

public class GmailApiUtil {

    private static final String APPLICATION_NAME = "ShoppingSite";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	private static final String TOKENS_DIRECTORY_PATH = System.getenv("TOKENS_DIRECTORY_PATH");
    
    private static final java.util.List<String> SCOPES = Collections.singletonList("https://www.googleapis.com/auth/gmail.send");
    private static final String CREDENTIALS_FILE_PATH = System.getenv("GOOGLE_CREDENTIALS_PATH");
    
    
    public static Credential getCredentials() throws Exception {
        String credPath = CREDENTIALS_FILE_PATH;
        System.out.println("DEBUG: loading credentials from " + credPath);
        File f = new File(credPath);
        System.out.println("DEBUG ▶ credPath  = [" + credPath + "]");
        System.out.println("DEBUG ▶ exists    = " + f.exists());
        System.out.println("DEBUG ▶ isFile    = " + f.isFile());
        System.out.println("DEBUG ▶ absPath   = " + f.getAbsolutePath());
        System.out.println("DEBUG ▶ canRead   = " + f.canRead());
        String tokenDir = System.getenv("TOKENS_DIRECTORY_PATH");
        System.out.println("DEBUG ▶ tokens dir = " + tokenDir);
        File tf = new File(tokenDir);
        System.out.println("DEBUG ▶ exists = " + tf.exists() + ", canRead = " + tf.canRead());
        try (FileInputStream in = new FileInputStream(credPath)) {
            var clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
            var flow = new GoogleAuthorizationCodeFlow.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JSON_FACTORY,
                    clientSecrets,
                    SCOPES
            )
            .setDataStoreFactory(new FileDataStoreFactory(Paths.get(TOKENS_DIRECTORY_PATH).toFile()))
            .setAccessType("offline")
            .build();
            Credential credential = flow.loadCredential("user");
            if (credential != null && credential.getRefreshToken() != null) {
                return credential;
            }
            var receiver = new LocalServerReceiver.Builder().setPort(8888).build();
            return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        } catch (FileNotFoundException e) {
            throw new Exception("credentials.json not found at " + credPath, e);
        }
    }

    public static void sendEmail(String to, String subject, String body) throws Exception {
        var credential = getCredentials();
        var service = new Gmail.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                credential
        ).setApplicationName(APPLICATION_NAME).build();

        MimeMessage email = createEmail(to, "me", subject, body);
        Message message = createMessageWithEmail(email);
        service.users().messages().send("me", message).execute();
    }

    private static MimeMessage createEmail(String to, String from, String subject, String bodyText) throws MessagingException {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);

        email.setFrom(new InternetAddress(from));
        email.addRecipient(jakarta.mail.Message.RecipientType.TO, new InternetAddress(to));
        email.setSubject(subject);
        email.setText(bodyText);

        return email;
    }

    private static Message createMessageWithEmail(MimeMessage email) throws Exception {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.getUrlEncoder().encodeToString(rawMessageBytes);
        Message message = new Message();
        message.setRaw(encodedEmail);
        return message;
    }
}
