package com.dvlima.rxjava.eventdriven.userservice;

public class CreateUserEvent extends UserEvent {

    public CreateUserEvent(String username, String emailAddress) {
        super(username, emailAddress);
    }

}
