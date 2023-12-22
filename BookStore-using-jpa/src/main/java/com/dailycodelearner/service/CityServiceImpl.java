package com.dailycodelearner.service;

import com.dailycodelearner.dto.requestDto.CityRequestDto;
import com.dailycodelearner.entity.City;
import com.dailycodelearner.repository.CityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City addCity(CityRequestDto cityRequestDto) {
        City city=new City();
        city.setName(cityRequestDto.getName());
        return cityRepository.save(city);
    }

    @Override
    public List<City> getCitites() {
        List<City> cities=new ArrayList<>();
        cityRepository.findAll().forEach(cities::add);
        return cities;
    }

    @Override
    public City getCity(Long cityId) {

        return cityRepository.findById(cityId).orElseThrow(()->new IllegalArgumentException("City with given" +
                "Id: "+cityId+"doesn't exsist"));
    }

    @Override
    public City deleteCity(Long cityId) {
        City city=getCity(cityId);
        cityRepository.delete(city);
        return city;
    }

    @Transactional
    @Override
    public City editCity(Long cityId, CityRequestDto cityRequestDto) {
        City cityToEdit=getCity(cityId);
        cityToEdit.setName(cityRequestDto.getName());
//        cityRepository.save(cityToEdit);
        return cityToEdit;
    }
}
