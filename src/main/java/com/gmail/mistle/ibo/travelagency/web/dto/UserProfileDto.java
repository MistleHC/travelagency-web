package com.gmail.mistle.ibo.travelagency.web.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileDto {

    @NotNull
    private String userFullName;

    @NotNull
    private String userDescription;
}
