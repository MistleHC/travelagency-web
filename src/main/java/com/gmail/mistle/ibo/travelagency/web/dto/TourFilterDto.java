package com.gmail.mistle.ibo.travelagency.web.dto;

import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourFilterDto {
    private String country;
    private String hotel;
    private String lowerPrice;
    private String higherPrice;
    private String lowerGroup;

    public void changeDefaultValues() {
        if (country == null) {
            country = "";
        }
        if (hotel == null) {
            hotel = "";
        }
        if (lowerPrice == null) {
            lowerPrice = "";
        }
        if (higherPrice == null) {
            higherPrice = "";
        }
        if (lowerGroup == null) {
            lowerGroup = "";
        }
    }
}
