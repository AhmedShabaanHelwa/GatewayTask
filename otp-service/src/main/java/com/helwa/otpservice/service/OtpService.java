package com.helwa.otpservice.service;

import com.helwa.otpservice.dto.OTPRequest;
import com.helwa.otpservice.dto.OTPResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpService {

    @NotNull
    public OTPResponse validateOtp(@NotNull OTPRequest otpRequest) {
        return getRandomOTPStatus(otpRequest);
    }

    @NotNull
    private OTPResponse getRandomOTPStatus(@NotNull OTPRequest otpRequest) {
        // TODO: Implement logic to validate OTP
        // For now, just return random status
        return new OTPResponse(new Random().nextInt(3));
    }
}
