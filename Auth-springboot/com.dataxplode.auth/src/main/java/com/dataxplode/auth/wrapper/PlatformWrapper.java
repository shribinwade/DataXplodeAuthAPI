package com.dataxplode.auth.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlatformWrapper {
    private Long platformId;
    private String platformName;
}
