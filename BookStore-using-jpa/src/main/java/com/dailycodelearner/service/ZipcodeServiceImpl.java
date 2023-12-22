package com.dailycodelearner.service;

import com.dailycodelearner.dto.requestDto.ZipcodeRequestDto;
import com.dailycodelearner.entity.City;
import com.dailycodelearner.entity.Zipcode;
import com.dailycodelearner.repository.ZipcodeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ZipcodeServiceImpl implements ZipcodeService {

    private final ZipcodeRepository zipcodeRepository;

    private final CityService cityService;

    @Autowired
    public ZipcodeServiceImpl(ZipcodeRepository zipcodeRepository, CityService cityService) {
        this.zipcodeRepository = zipcodeRepository;
        this.cityService = cityService;
    }

    @Transactional
    @Override
    public Zipcode addZipcode(ZipcodeRequestDto zipcodeRequestDto) {
        Zipcode zipcode=new Zipcode();
        zipcode.setName(zipcodeRequestDto.getName());
        if(zipcodeRequestDto.getCityId()!=null) {
            City city = cityService.getCity(zipcodeRequestDto.getCityId());
            zipcode.setCity(city);
        }
        return zipcodeRepository.save(zipcode);
    }

    @Override
    public List<Zipcode> getZipcodes() {
        List<Zipcode> zipcodes = new ArrayList<>();
        zipcodeRepository.findAll().forEach(zipcodes::add);
        return zipcodes;
    }

    @Override
    public Zipcode getZipcode(Long zipcodeId) {
        return zipcodeRepository.findById(zipcodeId).orElseThrow(()-> new IllegalArgumentException("Zipcode with" +
                "given zipcodeId: "+zipcodeId+" doesn't exsists"));
    }

    @Override
    public Zipcode deleteZipcode(Long zipcodeId) {
        Zipcode zipcode=getZipcode(zipcodeId);
        zipcodeRepository.delete(zipcode);
        return zipcode;
    }

    @Transactional
    @Override
    public Zipcode editZipcode(Long zipcodeId, ZipcodeRequestDto zipcodeRequestDto) {
        Zipcode zipcode=getZipcode(zipcodeId);
        zipcode.setName(zipcodeRequestDto.getName());
        if(zipcodeRequestDto.getCityId()==null){
            return zipcode;
        }
        City city=cityService.getCity(zipcodeRequestDto.getCityId());
        zipcode.setCity(city);
        return zipcode;
    }

    @Transactional
    @Override
    public Zipcode addCityToZipcode(Long zipcodeId, Long cityId) {
        Zipcode zipcode =getZipcode(zipcodeId);
        City city=cityService.getCity(cityId);
        if(Objects.nonNull(zipcode.getCity())){
            throw new IllegalArgumentException("zipcode has already a city");
        }
        zipcode.setCity(city);
        return zipcode;
    }

    @Transactional
    @Override
    public Zipcode removeCityFromZipCode(Long zipcodeId) {
        Zipcode zipcode =getZipcode(zipcodeId);
        if(!Objects.nonNull(zipcode.getCity())){
            throw new IllegalArgumentException("zipcode doesn't have a city");
        }
        zipcode.setCity(null);
        return zipcode;
    }
}
