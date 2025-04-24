package payk96.notification.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import payk96.notification.dto.OrderCreatedEvent;
import payk96.notification.service.EmailNotificationService;

@Service
@RequiredArgsConstructor
public class OrderCreatedEventListener {

    private final EmailNotificationService emailService;

    @KafkaListener(
            topics = "${kafka.topic.order-created}",
            groupId = "notification-service",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(OrderCreatedEvent event) {
        emailService.sendOrderCreatedEmail(event.customerId(), event);
    }
}

