package com.helwa.service.gateway.service;

import com.helwa.service.gateway.dto.LocalizedResponseDTO;
import com.helwa.service.gateway.dto.OtpDTO;
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
    public LocalizedResponseDTO localize(@NotNull OtpDTO response, String channel) {
        // Default to web if the channel is not specified
        if (channel == null || channel.isEmpty()) {
            channel = "web";
        }

        // Dynamically compose the key
        String statusKey = switch (response.statusCode()) {
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

        return new LocalizedResponseDTO(response.statusCode(), arMessage, enMessage, channel);
    }
}
