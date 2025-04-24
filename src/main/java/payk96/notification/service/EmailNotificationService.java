package payk96.notification.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import payk96.notification.dto.OrderCreatedEvent;
import payk96.notification.dto.UserRequest;
import payk96.notification.feign.UserServiceClient;

@Service
@RequiredArgsConstructor
public class EmailNotificationService {

    private final UserServiceClient userServiceClient;
    private final JavaMailSender mailSender;

    public void sendOrderCreatedEmail(String userId, OrderCreatedEvent event) {
        UserRequest user = userServiceClient.getUserById(userId);

        if (user.email() == null || user.email().isBlank()) {
            throw new IllegalStateException("User email not found for id: " + userId);
        }

        String subject = "Ваш заказ #" + event.orderId() + " оформлен";
        String body = buildEmailBody(user, event);

        sendEmail(user.email(), subject, body);
    }

    private String buildEmailBody(UserRequest user, OrderCreatedEvent event) {
        StringBuilder builder = new StringBuilder();

        builder.append("Здравствуйте, ").append(user.firstName()).append(" ").append(user.lastName()).append("!\n\n");
        builder.append("Ваш заказ №").append(event.orderId()).append(" успешно оформлен ").append(event.createdAt()).append(".\n");
        builder.append("Состав заказа:\n");

        for (OrderCreatedEvent.OrderItemDto item : event.items()) {
            builder.append("- ").append(item.name())
                    .append(" (").append(item.rarity()).append(" ")
                    .append(item.type()).append(") — ")
                    .append(item.price()).append(" монет\n");
        }

        builder.append("\nСпасибо за покупку!");

        return builder.toString();
    }

    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}


