package com.dailycodelearner.service;

import com.dailycodelearner.dto.requestDto.CityRequestDto;
import com.dailycodelearner.entity.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {

    public City addCity(CityRequestDto cityRequestDto);
    public List<City> getCitites();

    public City getCity(Long cityId);

    public City deleteCity(Long cityId);

    public City editCity(Long cityId,CityRequestDto cityRequestDto);
}
