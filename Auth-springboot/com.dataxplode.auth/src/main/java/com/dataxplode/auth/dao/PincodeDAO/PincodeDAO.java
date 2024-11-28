package com.dataxplode.auth.dao.PincodeDAO;

import com.dataxplode.auth.Models.pincodeModel.Pincode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PincodeDAO extends JpaRepository<Pincode,Long> {
    // Find pincode by value
    Optional<Pincode> findByPincode(String pincode);
}
