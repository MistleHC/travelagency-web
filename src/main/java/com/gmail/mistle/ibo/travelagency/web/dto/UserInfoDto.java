package com.gmail.mistle.ibo.travelagency.web.dto;

import com.gmail.mistle.ibo.travelagency.model.Order;
import com.gmail.mistle.ibo.travelagency.model.Role;
import lombok.*;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoDto {
    private String email;
    private String name;
    private String aboutMe;
    private String fullName;
    private Set<Role> roles;
    private Set<Order> orders;
}
