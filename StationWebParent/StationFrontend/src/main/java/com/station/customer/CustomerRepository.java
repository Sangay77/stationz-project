package com.station.customer;

import com.station.common.entity.AuthenticationType;
import com.station.common.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query("SELECT c FROM Customer c WHERE c.email=?1")
    Customer findByEmail(String email);

    @Query("SELECT c FROM Customer c WHERE c.verificationCode=?1")
    Customer findByVerificationCode(String code);

    @Query("UPDATE Customer c SET c.enabled=true,c.verificationCode=null WHERE c.id=?1")
    @Modifying
    void enable(Integer id);

    @Query("UPDATE Customer c SET c.authenticationType=?2 WHERE c.id=?1")
    @Modifying
    void updateAuthenticationType(Integer customerId, AuthenticationType authenticationType);

    Customer findByResetPasswordToken(String token);


}
