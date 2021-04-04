package be.fooda.backend.order.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class FoodaProductClient {

    private RestTemplate restClient;

    public boolean exist(Set<Long> collect) {
        return true;
    }
}
