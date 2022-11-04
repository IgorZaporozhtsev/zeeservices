package com.zeecoder.clients.acknowledgment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("acknowledgment")
public interface AcknowledgmentClient {

    @PostMapping("api/v1/acknowledgment")
    AcknowledgmentResponse apply(@RequestBody AcknowledgmentRequest request);
}
