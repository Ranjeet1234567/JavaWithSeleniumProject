package com.utils;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class EmailUtils {

    public static void sendEmailWithReport(String reportPath) {

        final String from = "ranjeetverma8826@gmail.com";
        final String password = "nwejiadlqqzxfhes"; // Gmail App Password
        String to = "ranjeetvermamub@gmail.com";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject("Automation Test Execution Report");
            // Email Body
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(
                    "Hi Team,\n\n" +
                            "Automation execution is completed.\n" +
                            "Please find the attached report.\n\n" +
                            "Regards,\nAutomation Team"
            );
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(reportPath);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("ExtentReport.html");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);
            System.out.println("✅ Report Email Sent Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}