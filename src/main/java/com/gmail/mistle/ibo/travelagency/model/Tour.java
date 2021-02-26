package com.gmail.mistle.ibo.travelagency.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tours")
public class Tour {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "country")
    private String country;

    @Column(name = "peoples")
    private Long peoples;

    @Column(name = "price")
    private Long price;

    @Column(name = "discount")
    private Long discount;

    @Column(name = "is_hot")
    private boolean isHot;

    @ManyToOne
    @JoinColumn(name = "hotel_type_id", nullable=false)
    private HotelType hotelType;

    @ManyToOne
    @JoinColumn(name = "tour_type_id", nullable=false)
    private TourType tourType;
}
