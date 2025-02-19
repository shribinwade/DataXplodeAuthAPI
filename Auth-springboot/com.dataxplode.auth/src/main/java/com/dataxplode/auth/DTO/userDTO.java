package com.dataxplode.auth.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class userDTO {

    private Long userId;
    private String username;
    private String email;
    private String contactNumber;
    private String userStatus;
    private boolean enabled;
    private roleDTO role; // Extracted from Role entity
    private userSubscriptionDTO subscription;
}
