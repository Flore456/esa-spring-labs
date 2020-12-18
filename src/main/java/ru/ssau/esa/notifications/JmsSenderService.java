package ru.ssau.esa.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import ru.ssau.esa.entity.*;

@Service
public class JmsSenderService {

    private final JmsTemplate jmsTemplate;


    @Autowired
    public JmsSenderService(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }


    public void sendAnimalUpdate(Animal animal, EventType eventType){
        Email email = new Email();
        Farmer farmer = animal.getFarmer();
        email.setReceiver(farmer.getEmail());
        email.setSubject("Animal [" + eventType.name() + ']');
        String bodyPattern = "Здравствуйте, %s %s!\n\n" +
                "Ваше животное изменено!\n" +
                "Тип изменения: %s\n\n" +
                "Животное: %s";
        String body = String.format(bodyPattern,
                farmer.getName(), farmer.getSurname(), eventType.name(), animal.toString());
        email.setBody(body);
        jmsTemplate.convertAndSend("mailbox", email);
    }

    public <T> void sendEvent(Class<T> entityClass, T entity, EventType eventType){
        Event event = new Event();
        event.setEventType(eventType);
        event.setEntity(entity.toString());
        event.setEntityClass(entityClass.getSimpleName());
        jmsTemplate.convertAndSend("eventbox", event);
    }


}
