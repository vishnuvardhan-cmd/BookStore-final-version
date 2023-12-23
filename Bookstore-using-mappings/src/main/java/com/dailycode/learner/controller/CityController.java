package com.dailycode.learner.controller;

import com.dailycode.learner.dto.requestdto.CityRequestDto;
import com.dailycode.learner.entity.City;
import com.dailycode.learner.service.CityService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping("/add")
    public ResponseEntity<City> addCity(@RequestBody final CityRequestDto cityRequestDto){
        City city=cityService.addCity(cityRequestDto);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<City> getCityById(@PathVariable final Long id){
        City city=cityService.getCity(id);
        return new ResponseEntity<>(city,HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<City>> getAllCities(){
        List<City> cities=cityService.getCities();
        return new ResponseEntity<>(cities,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<City> deleteById(@PathVariable final Long id){
        City city=cityService.deleteCity(id);
        return new ResponseEntity<>(city,HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<City> editCity(@RequestBody final CityRequestDto cityRequestDto,@PathVariable final Long id){
        City city=cityService.editCity(id,cityRequestDto);
        return new ResponseEntity<>(city,HttpStatus.OK);
    }

}
