package com.helwa.service.translation.controller;

import com.helwa.service.translation.dto.OtpRequest;
import com.helwa.service.translation.dto.OtpResponse;
import com.helwa.service.translation.service.TranslationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/translate")
public class TranslationController {

    private final TranslationService translationService;

    public TranslationController(TranslationService translationService) {
        this.translationService = translationService;
    }


    @PostMapping("/otp")
    public Mono<ResponseEntity<OtpResponse>> localizeOtpResponse(
            @RequestBody OtpRequest otpRequest,
            @RequestHeader(name = "channel", defaultValue = "web") String channel) {

        return Mono.just(ResponseEntity.ok(translationService.translate(otpRequest, channel)));
    }
}
