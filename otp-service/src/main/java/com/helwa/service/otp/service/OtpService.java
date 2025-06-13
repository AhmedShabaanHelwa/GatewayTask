package com.helwa.service.otp.service;

import com.helwa.service.otp.dto.OTPRequest;
import com.helwa.service.otp.dto.OtpResponse;
import com.helwa.service.otp.dto.OtpLocalizationRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Random;

@Service
public class OtpService {

    private final WebClient webClient;

    public OtpService(@Value("${localizationService.url}") String localizationServiceUrl) {
        // TODO: Localization service url to be obtained dynamically using service discovery, Just hardcode it for now
        webClient = WebClient.create(localizationServiceUrl);
    }

    @NotNull
    public Mono<OtpResponse> validateOtp(@NotNull OTPRequest otpRequest) {
        // TODO: Implement logic to validate OTP
        // For now, just return random status
        int statusCode = getRandomOTPStatus(otpRequest);

        Mono<OtpLocalizationRequest> otpLocalizationRequest = Mono.just(new OtpLocalizationRequest(statusCode));
        return localize(otpLocalizationRequest);
    }

    private int getRandomOTPStatus(@NotNull OTPRequest otpRequest) {
        // TODO: Implement logic to validate OTP
        // For now, just return random status
        return new Random().nextInt(3);
    }

    @NotNull
    private Mono<OtpResponse> localize(@NotNull Mono<OtpLocalizationRequest> otpLocalizationRequest) {

        return webClient.post()
                .uri("/otp")
                .body(otpLocalizationRequest, OtpLocalizationRequest.class)
                .retrieve()
                .bodyToMono(OtpResponse.class);
    }
}
