package com.booking.recruitment.hotel.repository;

import com.booking.recruitment.hotel.model.Hotel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query("Select h, (6371*acos(cos(radians(:latitude))* cos(radians(h*latitude)) *cos(radians(h*longitude) -radians(:longitude)) + sin(radians(:latitude)) *sin(radians(h.latitude)))) as distance from Hotel h order by distance asc")
    List<Object[]> findClosestHotels(@Param("latitude") double latitude, @Param("longitude") double longitude, Pageable pageable);

}
