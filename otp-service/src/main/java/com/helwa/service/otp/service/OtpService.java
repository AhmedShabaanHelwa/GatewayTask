package com.helwa.service.otp.service;

import com.helwa.service.otp.dto.OTPRequest;
import com.helwa.service.otp.dto.OtpResponse;
import com.helwa.service.otp.dto.OtpTranslationRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Random;

@Service
public class OtpService {

    private final WebClient webClient;
    private static final String TRANSLATION_SERVICE_URL = "http://localhost:5000/translate";

    public OtpService() {
        // TODO: Translation service url to be obtained dynamically using service discovery, Just hardcode it for now
            webClient = WebClient.create(TRANSLATION_SERVICE_URL);
    }

    @NotNull
    public Mono<OtpResponse> validateOtp(@NotNull OTPRequest otpRequest) {
        // TODO: Implement logic to validate OTP
        // For now, just return random status
        int statusCode = getRandomOTPStatus(otpRequest);

        Mono<OtpTranslationRequest> otpTranslationRequest = Mono.just(new OtpTranslationRequest(statusCode));
        return translate(otpTranslationRequest);
    }

    private int getRandomOTPStatus(@NotNull OTPRequest otpRequest) {
        // TODO: Implement logic to validate OTP
        // For now, just return random status
        return new Random().nextInt(3);
    }

    @NotNull
    private Mono<OtpResponse> translate(@NotNull Mono<OtpTranslationRequest> otpTranslationRequest) {

        return webClient.post()
                .uri("/otp")
                .body(otpTranslationRequest, OtpTranslationRequest.class)
                .retrieve()
                .bodyToMono(OtpResponse.class);
    }
}
