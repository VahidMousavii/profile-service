package io.devotel.profileservice.soap.client;

import io.devotel.profileservice.soap.wsdl.GetUserByIdRequest;
import io.devotel.profileservice.soap.wsdl.GetUserByIdResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserSoapClient {

    private final WebServiceTemplate webServiceTemplate;

    public GetUserByIdResponse getUserById(Long userId) {
        GetUserByIdRequest request = new GetUserByIdRequest();
        request.setId(userId);

        Object response = webServiceTemplate.marshalSendAndReceive(request);

        if (response == null) {
            log.error("SOAP response is null for userId={}", userId);
            throw new RuntimeException("Received null response from User SOAP service");
        }

        if (response instanceof GetUserByIdResponse userResponse) {
            return userResponse;
        }

        log.error("Unexpected response type from User SOAP service: {}", response.getClass().getName());
        throw new RuntimeException("Unexpected response from User SOAP service.");
    }
}
