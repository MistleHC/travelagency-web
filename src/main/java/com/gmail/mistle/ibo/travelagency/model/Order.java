package com.gmail.mistle.ibo.travelagency.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id",
                referencedColumnName = "id",
                nullable = false)
    private User customer;

    @ManyToOne
    @JoinColumn(name = "tour_id",
            referencedColumnName = "id",
            nullable = false)
    private Tour tour;

    @ManyToOne
    @JoinColumn(name = "status_id",
                referencedColumnName = "id",
                nullable = false)
    private Status status;

}
