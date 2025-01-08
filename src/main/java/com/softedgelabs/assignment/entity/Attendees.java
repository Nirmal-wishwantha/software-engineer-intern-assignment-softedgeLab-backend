package com.softedgelabs.assignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attendeeId;
    private String attendeeName;
    private String attendeeEmail;

    @ManyToOne
    @JoinColumn(name = "registered_events")
    private Events event;

}
