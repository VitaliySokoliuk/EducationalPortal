package ua.lviv.EduPortal.Services;

import ua.lviv.EduPortal.Entities.User;

import java.util.UUID;

public class SendEmail implements Runnable{

    EmailSendingService emailSendingService;
    User user;
    UUID uuid;

    public SendEmail(EmailSendingService emailSendingService, User user, UUID uuid) {
        this.emailSendingService = emailSendingService;
        this.user = user;
        this.uuid = uuid;
    }

    @Override
    public void run() {
        emailSendingService.sendEmail(user.getEmail(), uuid.toString());
    }

}