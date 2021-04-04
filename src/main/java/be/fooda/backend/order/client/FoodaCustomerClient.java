package be.fooda.backend.order.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class FoodaCustomerClient {

    private RestTemplate restClient;

    public boolean exist(Long externalCustomerId) {
        return true;
    }
}
