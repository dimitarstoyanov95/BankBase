package com.stoyanov.gateway.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileWithPasswordDTO {

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
