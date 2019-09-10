package com.dvlima.rxjava.eventdriven.emailmonitor;

import com.dvlima.rxjava.eventdriven.emailservice.EmailService;
import com.dvlima.rxjava.eventdriven.userservice.UserEvent;
import com.dvlima.rxjava.eventdriven.userservice.UserService;

import java.util.ArrayList;

public class EmailMonitor {

    private final EmailService emailService;

    public EmailMonitor(EmailService emailService, UserService userService) {
        this.emailService = emailService;

        // Subscribe to UserEvents in the userService
        userService.subscribeToUserEvents(this::handleUserEvent);
    }

    private void handleUserEvent(UserEvent t) {
        System.out.println("EmailMonitorServiceImpl::handleUserEvent - " + Thread.currentThread().getName());

        ArrayList<String> recipList = new ArrayList<>();
        recipList.add(t.getEmailAddress());
        String text = "Hi " + t.getUsername() + ", Welcome to RxJava!";
        emailService.sendEmail(recipList, "noreply@mySystem.com", "Welcome to RxJava!", text);
    }

}
