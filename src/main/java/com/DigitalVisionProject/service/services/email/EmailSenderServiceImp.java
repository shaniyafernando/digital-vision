package com.DigitalVisionProject.service.services.email;

public interface EmailSenderServiceImp {

    void send(String subject,String to,String email);

    void send(String to, String email);
}
