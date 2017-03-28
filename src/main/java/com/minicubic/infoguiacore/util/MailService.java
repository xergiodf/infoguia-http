package com.minicubic.infoguiacore.util;

import java.io.IOException;
import java.util.List;
import javax.mail.util.ByteArrayDataSource;
import lombok.extern.java.Log;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author hectorvillalba
 */
@Log
public class MailService {

    public static void sendeEmail(String subject,
                                  String mensaje,
                                  List<String> emails,
                                  byte[] content,
                                  String nameAttachment
    ) throws EmailException, IOException {
        EmailAttachment attachment = new EmailAttachment();
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Archivo Adjunto");
        attachment.setName(nameAttachment);

        // Create the email message
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName(Constants.HOST_NAME_SMTP);
        email.setSSLOnConnect(true);
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(Constants.USER_NAME_SMTP,Constants.PASS_SMTP));
        for (String s : emails){
            email.addTo(s);
        }
        //email.addCc(config.getMailCC());
        email.setFrom(Constants.USER_NAME_SMTP, "ENEM");
        email.setSubject(subject);
        email.setMsg(mensaje);

        // add the attachment
        String extension = nameAttachment.substring(nameAttachment.length()-3);
        String nameWithoutExtension = nameAttachment.substring(0,nameAttachment.length()-4);
        email.attach(new ByteArrayDataSource(content, "application/" +extension), nameWithoutExtension, "Archivo Adjunto", EmailAttachment.ATTACHMENT);
        // send the email
        email.send();
    }

    public static void sendeEmail(String subject,
                                  String mensaje,
                                  List<String> emails) throws EmailException, IOException {

        // Create the email message
        Email email = new SimpleEmail();
        email.setCharset("UTF-8");
        email.setHostName(Constants.HOST_NAME_SMTP);
        email.setSmtpPort(465);
        email.setSSLOnConnect(true);
        email.setAuthenticator(new DefaultAuthenticator(Constants.USER_NAME_SMTP, Constants.PASS_SMTP));
        for (String s : emails){
            email.addTo(s);
        }
        //email.addCc(config.getMailCC());
        email.setFrom(Constants.USER_NAME_SMTP, "ENEM");
        email.setSubject(subject);
        email.setMsg(mensaje);
        // send the email
        email.send();
    }
}
