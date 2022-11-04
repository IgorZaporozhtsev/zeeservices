package com.zeecoder.acknowledgment;

import com.zeecoder.clients.acknowledgment.AcknowledgmentRequest;
import com.zeecoder.clients.acknowledgment.AcknowledgmentResponse;
import org.springframework.stereotype.Service;

@Service
public class AcknowledgmentService {

    private final AcknowledgmentRepository repository;

    public AcknowledgmentService(AcknowledgmentRepository repository) {
        this.repository = repository;
    }

    public AcknowledgmentResponse checkStatus(AcknowledgmentRequest request) {
        Acknowledgment acknowledgment = Acknowledgment.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .status(AcknowledgmentStatus.APPROVED).build();

        var saved =  repository.save(acknowledgment);

        return new AcknowledgmentResponse(saved.status.name());
    }
}
