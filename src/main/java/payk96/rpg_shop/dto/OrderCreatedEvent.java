package payk96.rpg_shop.dto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderCreatedEvent(
        Long orderId,
        String customerId,
        List<OrderItemDto> items,
        LocalDateTime createdAt
) {
    public record OrderItemDto(
            Long id,
            String name,
            String type,
            String rarity,
            int price
    ) {}
}

