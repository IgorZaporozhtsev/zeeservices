package com.zeecoder.acknowledgment;

import com.zeecoder.clients.acknowledgment.AcknowledgmentRequest;
import com.zeecoder.clients.acknowledgment.AcknowledgmentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/acknowledgment")
@Slf4j
public class AcknowledgmentController {

    private final AcknowledgmentService service;

    public AcknowledgmentController(AcknowledgmentService service) {
        this.service = service;
    }

    @PostMapping
    AcknowledgmentResponse apply(@RequestBody AcknowledgmentRequest request){
        log.info("Acknowledgment for.... {}, {}", request.firstName(), request.lastName());
        return service.checkStatus(request);
    }

}
