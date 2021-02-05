package com.gmail.mistle.ibo.travelagency.web.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourCreationDto {

    @NotNull
    private String tourName;

    @NotNull
    private String tourDescription;

    @NotNull
    private String tourType;

    @NotNull
    private String tourCountry;

    @NotNull
    private long tourSize;

    @NotNull
    private String tourHotel;

    @NotNull
    private long tourPrice;
}
