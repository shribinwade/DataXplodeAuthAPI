package com.dataxplode.auth.service.PlatformService;


import com.dataxplode.auth.wrapper.PlatformWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface PlatformService {

    ResponseEntity<String> createPlatform(Map<String, String> requestMap);
    ResponseEntity<String> deletePlatform(Map<String, String> requestMap);
    ResponseEntity<List<PlatformWrapper>> getAllPlatform();
}
