package io.devotel.profileservice.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class SoapConfig {

    @Value("${soap.user-service.uri}")
    private String userServiceUri;

    @Bean
    public Jaxb2Marshaller userServiceMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("io.devotel.profileservice.soap.wsdl"); // بسته‌ای که wsimport ساخت
        return marshaller;
    }

    @Bean
    public WebServiceTemplate userWebServiceTemplate(Jaxb2Marshaller userServiceMarshaller) {
        WebServiceTemplate template = new WebServiceTemplate();
        template.setMarshaller(userServiceMarshaller);
        template.setUnmarshaller(userServiceMarshaller);
        template.setDefaultUri(userServiceUri);
        return template;
    }
}
