package ru.ssau.esa.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import ru.ssau.esa.entity.Email;
import ru.ssau.esa.entity.Event;
import ru.ssau.esa.repos.EmailRepo;
import ru.ssau.esa.repos.EventRepo;

@Component
public class JmsReceiver {

    private final EmailSenderService emailSenderService;
    private final EventRepo eventRepo;
    private final EmailRepo emailRepo;

    @Autowired
    public JmsReceiver(EmailSenderService emailSenderService, EventRepo eventRepo, EmailRepo emailRepo) {
        this.emailSenderService = emailSenderService;
        this.eventRepo = eventRepo;
        this.emailRepo = emailRepo;
    }

    @JmsListener(destination = "eventbox", containerFactory = "myFactory")
    public void receiveEvent(Event event) {
        eventRepo.save(event);
    }

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(Email email) {
        emailSenderService.send(email);
        emailRepo.save(email);
    }
}
