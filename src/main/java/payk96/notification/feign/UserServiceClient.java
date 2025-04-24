package payk96.notification.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import payk96.notification.dto.UserRequest;

@FeignClient(name = "user-service", url = "${user-service.url}")
public interface UserServiceClient {

    @GetMapping("/auth/user/{id}")
    UserRequest getUserById(@PathVariable("id") String id);
}

