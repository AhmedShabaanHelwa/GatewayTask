package com.helwa.service.gateway.service;

import com.helwa.service.gateway.dto.LocalizedResponseDTO;
import com.helwa.service.gateway.dto.OtpDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class LocalizationService {

    @NotNull
    public LocalizedResponseDTO localize(@NotNull OtpDTO response, String channel) {
        return null;
    }
}
