package com.dataxplode.auth.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PincodeWrapper {
    private Integer pincodeId;
    private String pincode;
}
