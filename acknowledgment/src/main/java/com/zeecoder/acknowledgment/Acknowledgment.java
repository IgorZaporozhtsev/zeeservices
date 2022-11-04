package com.zeecoder.acknowledgment;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Acknowledgment {

    @Id
    @SequenceGenerator(
            name = "customer_id_sequence",
            sequenceName = "customer_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence"
    )
    Integer acknowledgmentId;

    @Enumerated(EnumType.STRING)
    AcknowledgmentStatus status;

    String firstName;
    String lastName;


}
