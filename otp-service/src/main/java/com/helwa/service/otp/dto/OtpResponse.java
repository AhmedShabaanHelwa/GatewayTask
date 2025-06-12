package com.helwa.service.otp.dto;

public record OtpResponse(int statusCode, String message_ar, String message_en, String channel) {
    // TODO: Possible improvement: To make status code as enum to enhance readability
}
