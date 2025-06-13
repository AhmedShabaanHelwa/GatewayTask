package com.helwa.service.localization.service;

import com.helwa.service.localization.dto.OtpRequest;
import com.helwa.service.localization.dto.OtpResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocalizationService {

    private final MessageSource messageSource;

    public LocalizationService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @NotNull
    public OtpResponse localize(@NotNull OtpRequest otpRequest, @NotNull String channel) {

        // Default to web if the channel is not specified
        if (channel.isEmpty()) {
            channel = "web";
        }

        // Dynamically compose the key
        String statusKey = switch (otpRequest.statusCode()) {
            case 0 -> "otp.unknown";
            case 1 -> "otp.success";
            case 2 -> "otp.invalid";
            case 3 -> "otp.expired";
            default -> "otp.unknown";
        };

        // Create messages
        String enMessage = messageSource.getMessage(
                String.format("%s.en.%s", channel, statusKey),
                null, // No arguments for messages
                Locale.ENGLISH
        );

        String arMessage = messageSource.getMessage(
                String.format("%s.ar.%s", channel, statusKey),
                null,
                Locale.forLanguageTag("ar")
        );

        return new OtpResponse(otpRequest.statusCode(), arMessage, enMessage, channel);
    }
}
