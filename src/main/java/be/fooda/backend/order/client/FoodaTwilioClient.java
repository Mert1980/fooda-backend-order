package be.fooda.backend.order.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class FoodaTwilioClient {

    private final RestTemplate restTemplate;

    public ResponseEntity<String> sendSmsToCustomer( String customerPhone,  String messageContent) {
        return ResponseEntity.ok(messageContent);
    }


}
