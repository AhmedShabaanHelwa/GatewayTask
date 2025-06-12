package com.helwa.service.otp.controller;

import com.helwa.service.otp.dto.OTPRequest;
import com.helwa.service.otp.dto.OtpResponse;
import com.helwa.service.otp.service.OtpService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/otp")
public class OTPController {

    private final OtpService otpService;

    public OTPController(OtpService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/validate")
    public Mono<ResponseEntity<OtpResponse>> validateOtp(@RequestBody OTPRequest request) {
        return otpService.validateOtp(request)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}
