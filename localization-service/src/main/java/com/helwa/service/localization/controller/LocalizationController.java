package com.helwa.service.localization.controller;

import com.helwa.service.localization.dto.OtpRequest;
import com.helwa.service.localization.dto.OtpResponse;
import com.helwa.service.localization.service.LocalizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/localize")
public class LocalizationController {

    private final LocalizationService localizationService;

    public LocalizationController(LocalizationService localizationService) {
        this.localizationService = localizationService;
    }


    @PostMapping("/otp")
    public Mono<ResponseEntity<OtpResponse>> localizeOtpResponse(
            @RequestBody OtpRequest otpRequest,
            @RequestHeader(name = "channel", defaultValue = "web") String channel) {

        return Mono.just(ResponseEntity.ok(localizationService.localize(otpRequest, channel)));
    }
}
