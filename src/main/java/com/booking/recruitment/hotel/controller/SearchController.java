package com.booking.recruitment.hotel.controller;

import com.booking.recruitment.hotel.service.CityService;
import com.booking.recruitment.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final HotelService hotelService;
    private final CityService cityService;

    @Autowired
    public SearchController(HotelService hotelService, CityService cityService) {
        this.hotelService = hotelService;
        this.cityService = cityService;
    }

    @GetMapping("{cityId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> findClosedHotels(@PathVariable Long cityId, @RequestParam String sortBy){

        double cityCenterLongitude = cityService.getCityCenterLongitudeByCityId(cityId);
        double cityCenterLatitude  = cityService.getCityCenterLatitudeByCityId(cityId);

        if("distance".equals(sortBy)){
            return hotelService.findClosestHotels(cityCenterLatitude, cityCenterLongitude);
        }else {
            return new ArrayList<>();
        }
    }
}

