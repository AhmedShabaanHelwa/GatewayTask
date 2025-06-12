package com.helwa.service.translation.dto;

public record OtpResponse(int statusCode, String message_ar, String message_en, String channel) {

    // TODO: For simplicity, we return messages explicitly in the same JSON, Messages to be encapsulated in a separate JSON object and injected into the response.
}
