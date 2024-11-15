package com.dataxplode.auth.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlatformWrapper {
    private Integer platformId;
    private String platformName;
}
