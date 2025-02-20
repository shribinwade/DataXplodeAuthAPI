package com.dataxplode.auth.dao;

import com.dataxplode.auth.Models.UsersAndUserSubscriptionModels.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    User findByEmailId(@Param("email") String email);

    List <User> getAllUser();

    List <String> getAllAdmin();

    @Transactional
    @Modifying
    Integer updateStatus(@Param("status") String status,@Param("id") Integer id);

    User findByEmail(String email);

    User findByVerificationToken(String verificationToken);

    User findByContactNumber(String contactNumber);

    Optional<User> findByUserId(Long userId);


}
