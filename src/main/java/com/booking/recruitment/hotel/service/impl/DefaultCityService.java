package com.booking.recruitment.hotel.service.impl;

import com.booking.recruitment.hotel.exception.BadRequestException;
import com.booking.recruitment.hotel.exception.ElementNotFoundException;
import com.booking.recruitment.hotel.model.City;
import com.booking.recruitment.hotel.model.Hotel;
import com.booking.recruitment.hotel.repository.CityRepository;
import com.booking.recruitment.hotel.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class DefaultCityService implements CityService {
  private final CityRepository cityRepository;

  @Autowired
  DefaultCityService(CityRepository cityRepository) {
    this.cityRepository = cityRepository;
  }

  @Override
  public City getCityById(Long id) {
    return cityRepository
        .findById(id)
        .orElseThrow(() -> new ElementNotFoundException("Could not find city with ID provided"));
  }

  @Override
  public List<City> getAllCities() {
    return cityRepository.findAll();
  }

  @Override
  public City createCity(City city) {
    if (city.getId() != null) {
      throw new BadRequestException("The ID must not be provided when creating a new City");
    }

    return cityRepository.save(city);
  }

  @Override
  public double getCityCenterLatitudeByCityId(Long cityId) {
    Optional<City> cityOptional = cityRepository.findById(cityId);
    if(!cityOptional.isPresent()){
      throw new ElementNotFoundException("City not found : City ID- "+ cityId);
    }
    return cityOptional.get().getCityCentreLatitude();
  }

  @Override
  public double getCityCenterLongitudeByCityId(Long cityId) {
    Optional<City> cityOptional = cityRepository.findById(cityId);
    if(!cityOptional.isPresent()){
      throw new ElementNotFoundException("City not found : City ID- "+ cityId);
    }
    return cityOptional.get().getCityCentreLongitude();
  }
}
