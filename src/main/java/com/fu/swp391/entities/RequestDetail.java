package com.fu.swp391.entities;

import javax.persistence.*;

@Entity
@Table(name = "request_detail")
public class RequestDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "request_id", nullable = false, updatable = false)
    private Request request;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "major_id", nullable = false, updatable = false)
    private Major major;


}
