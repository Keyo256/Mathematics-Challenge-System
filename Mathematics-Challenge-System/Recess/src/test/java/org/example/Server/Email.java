package org.example.Server;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

    public class Email{
        // SMTP server host
        String host = "smtp.gmail.com";

        // Gmail account credentials for sending emails
        String username = "eelection3.portal@gmail.com";
        String from = "eelection3.portal@gmail.com";
        String password = "mngv mtyl wmzk exmi";
        Session session;

        // Constructor
        public Email() {
            // Set up properties for the SMTP session
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");

            // Initialize session with authentication
            String username = this.username;
            String password = this.password;
            this.session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            this.session.setDebug(true);
        }

        // Method to send participant registration request email
        public void sendParticipantRegistrationRequestEmail(String to, String participantEmail, String username) throws MessagingException {
            // Create new MimeMessage for sending email
            MimeMessage message = new MimeMessage(this.session);

            // Set sender and recipient addresses
            message.setFrom(new InternetAddress(this.from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set email subject
            message.setSubject("Notification of Participant registration under your school");

            // Construct email message body
            StringBuilder emailMessage = new StringBuilder();
            emailMessage.append("New participant notification\n\n");
            emailMessage.append("This message is to notify you that there is a new participant request from your school \n\n");
            emailMessage.append("Their details are as follows:\n");
            emailMessage.append("\tUsername: ").append(username).append("\n");
            emailMessage.append("\tEmail: ").append(participantEmail).append("\n\n");
            emailMessage.append(" please login into the command line interface and confirm using commands:\n");
            emailMessage.append("\tconfirm with:-> confirm yes/no ").append(username).append("\n");

            // Set email message text
            message.setText(emailMessage.toString());

            // Send the email message
            Transport.send(message);
        }
        public void sendRejectedParticipantEmail(String to, String username) throws MessagingException {
            MimeMessage message = new MimeMessage(this.session);

            // Set sender and recipient addresses
            message.setFrom(new InternetAddress(this.from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set email subject
            message.setSubject("Notification about your rejection");

            // Construct email message body
            StringBuilder emailMessage = new StringBuilder();
            emailMessage.append("Rejected participant notification\n\n");
            emailMessage.append("This message is to notify you that you have been denied from taking part in the math challenge \n\n");
            emailMessage.append("Contact your school representative for more information about your rejection\n");
            emailMessage.append(" we are sorry, try again next time:\n");


            // Set email message text
            message.setText(emailMessage.toString());

            // Send the email message
            Transport.send(message);
        }
        public void sendConfirmedParticipantEmail(String to, String username) throws MessagingException {
            // Create new MimeMessage for sending email
            MimeMessage message = new MimeMessage(this.session);

            // Set sender and recipient addresses
            message.setFrom(new InternetAddress(this.from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set email subject
            message.setSubject("Notification for confirmation");

            // Construct email message body
            StringBuilder emailMessage = new StringBuilder();
            emailMessage.append("confirmation notification\n\n");
            emailMessage.append("This message is to notify you that you have been confirmed to take part in the math challenge \n\n");
            emailMessage.append("Their details are as follows:\n");
            emailMessage.append(" please login into the command line interface to attempt the challenges:\n");

            // Set email message text
            message.setText(emailMessage.toString());

            // Send the email message
            Transport.send(message);
        }
    }

