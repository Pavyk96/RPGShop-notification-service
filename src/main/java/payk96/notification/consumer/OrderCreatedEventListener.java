package payk96.notification.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import payk96.notification.service.EmailNotificationService;
import payk96.rpg_shop.dto.OrderCreatedEvent;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderCreatedEventListener {

    private final EmailNotificationService emailService;

    @KafkaListener(
            topics = "${kafka.topic.order-created}",
            groupId = "notification-service",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(OrderCreatedEvent event) {
        log.info("📩 Получено событие: {}", event);
        emailService.sendOrderCreatedEmail(event.customerId(), event);
    }
}

