package com.gmail.mistle.ibo.travelagency.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id",
                referencedColumnName = "id",
                nullable = false)
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id",
            referencedColumnName = "id",
            nullable = false)
    private Tour tour;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id",
                referencedColumnName = "id",
                nullable = false)
    private Status status;

}
