package com.booking.recruitment.hotel.service.impl;

import com.booking.recruitment.hotel.exception.BadRequestException;
import com.booking.recruitment.hotel.exception.ElementNotFoundException;
import com.booking.recruitment.hotel.model.Hotel;
import com.booking.recruitment.hotel.repository.HotelRepository;
import com.booking.recruitment.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
class DefaultHotelService implements HotelService {
  private final HotelRepository hotelRepository;

  @Autowired
  DefaultHotelService(HotelRepository hotelRepository) {
    this.hotelRepository = hotelRepository;
  }

  @Override
  public List<Hotel> getAllHotels() {
    return hotelRepository.findAll();
  }

  @Override
  public List<Hotel> getHotelsByCity(Long cityId) {
    return hotelRepository.findAll().stream()
        .filter((hotel) -> cityId.equals(hotel.getCity().getId()))
        .collect(Collectors.toList());
  }

  @Override
  public Hotel createNewHotel(Hotel hotel) {
    if (hotel.getId() != null) {
      throw new BadRequestException("The ID must not be provided when creating a new Hotel");
    }

    return hotelRepository.save(hotel);
  }

  @Override
  public Hotel getHotelById(Long id) {
    Optional<Hotel> hotelOptional = hotelRepository.findById(id);
    if(!hotelOptional.isPresent()){
      throw new ElementNotFoundException("Hotel not found : Hotel ID- "+ id);
    }
    return hotelOptional.get();
  }

  @Override
  public void deleteHotelById(Long id) {
    Optional<Hotel> hotelOptional = hotelRepository.findById(id);
    if(!hotelOptional.isPresent()){
      throw new ElementNotFoundException("Hotel not found : Hotel ID- "+ id);
    }
    Hotel hotel = hotelOptional.get().builder().setDeleted(true).build();
    /**
     * System.out.println(hotel);
     */
  }

  public List<Object[]> findClosestHotels(double latitude, double longitude){
    return hotelRepository.findClosestHotels(latitude,longitude, PageRequest.of(0,3));
  }

}
