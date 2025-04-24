package payk96.notification.dto;

import lombok.Builder;

@Builder
public record UserRequest(
        String id,
        String username,
        String email,
        String firstName,
        String lastName
) { }
