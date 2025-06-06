package com.station.admin.shippingrate;

import com.station.common.entity.ShippingRate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ShippingRateRepository extends JpaRepository<ShippingRate, Integer> {

    @Query("SELECT sr FROM ShippingRate sr WHERE sr.country.id=?1 AND sr.state=?2")
    ShippingRate findByCountryAndState(Integer countryId, String state);

    @Query("UPDATE ShippingRate sr SET sr.codeSupported=?2 WHERE sr.id=?1")
    @Modifying
    void updateCODSupport(Integer id, boolean enabled);

    @Query("SELECT sr FROM ShippingRate sr WHERE sr.country.name LIKE %?1% OR sr.state LIKE %?1%")
    public Page<ShippingRate> findAll(String keyword, Pageable pageable);

    Long countById(Integer id);
}
