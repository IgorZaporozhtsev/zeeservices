package com.zeecoder.acknowledgment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcknowledgmentRepository extends JpaRepository<Acknowledgment, Integer>{

}