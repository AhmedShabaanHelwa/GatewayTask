package com.helwa.service.gateway.config;

import com.helwa.service.gateway.dto.LocalizedResponseDTO;
import com.helwa.service.gateway.dto.OtpDTO;
import com.helwa.service.gateway.service.LocalizationService;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfiguration {

    private final LocalizationService localizationService;

    public GatewayConfiguration(LocalizationService localizationService) {

        this.localizationService = localizationService;
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {

        return builder.routes()
                .route("OTPService", route ->
                        route.path("/otp/**")
                                .filters(f -> f.modifyResponseBody(
                                                OtpDTO.class,
                                                LocalizedResponseDTO.class,
                                                (serverWebExchange, otpDTO) -> {
                                                    // Get channel header
                                                    String channel = serverWebExchange.getRequest().getHeaders().getFirst("channel");

                                                    // Modifying the response body
                                                    return Mono.just(localizationService.localize(otpDTO, channel));
                                                }
                                        ) // End of modifyResponseBody()
                                ) // End of filters()
                                .uri("http://localhost:7000") // Keep It Simple: Hardcoded but should be using service discovery
                ) // End of route()
                .build();
    }
}
