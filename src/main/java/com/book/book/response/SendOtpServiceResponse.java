package com.book.book.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SendOtpServiceResponse {

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("type")
    private String type;
}
