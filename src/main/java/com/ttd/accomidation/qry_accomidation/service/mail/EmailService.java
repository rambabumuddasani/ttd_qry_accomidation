package com.ttd.accomidation.qry_accomidation.service.mail;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendSimpleMessage(
            String[] to, String subject, String text);

    void sendMessageWithAttachment(
            String to, String subject, String text, String pathToAttachment) throws MessagingException;
}
