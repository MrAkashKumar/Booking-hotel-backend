package com.booking.recruitment.hotel.controller;

import com.booking.recruitment.hotel.model.Hotel;
import com.booking.recruitment.hotel.service.CityService;
import com.booking.recruitment.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/hotel")
public class HotelController {
  private final HotelService hotelService;

  @Autowired
  public HotelController(HotelService hotelService) {
    this.hotelService = hotelService;

  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Hotel> getAllHotels() {
    return hotelService.getAllHotels();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Hotel createHotel(@RequestBody Hotel hotel) {
    return hotelService.createNewHotel(hotel);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Hotel getHotelById(@PathVariable Long id){
    return hotelService.getHotelById(id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Objects> deleteHotelById(@PathVariable Long id){
    hotelService.deleteHotelById(id);
    return ResponseEntity.ok().build();
  }



}
