package com.dailycode.learner.controller;

import com.dailycode.learner.dto.requestdto.ZipcodeRequestDto;
import com.dailycode.learner.entity.Zipcode;
import com.dailycode.learner.service.ZipcodeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zipcode")
public class ZipcodeController {

    private final ZipcodeService zipcodeService;

    @Autowired
    public ZipcodeController(ZipcodeService zipcodeService) {
        this.zipcodeService = zipcodeService;
    }

    @PostMapping("/add")
    public ResponseEntity<Zipcode> addZipcode(@RequestBody final ZipcodeRequestDto zipcodeRequestDto){
      Zipcode zipcode = zipcodeService.addZipcode(zipcodeRequestDto);
      return new ResponseEntity<>(zipcode, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Zipcode> getCity(@PathVariable final long id){
        Zipcode zipcode= zipcodeService.getZipcode(id);
       return new ResponseEntity<>(zipcode,HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Zipcode>> getAllCities(){
        List<Zipcode> zipcodes=zipcodeService.getZipcodes();
        return new ResponseEntity<>(zipcodes,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Zipcode> deleteCity(@PathVariable final long id){
        Zipcode zipcode=zipcodeService.getZipcode(id);
        return new ResponseEntity<>(zipcode,HttpStatus.OK);
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Zipcode> editZipcode(@RequestBody final ZipcodeRequestDto zipcodeRequestDto,
                                               @PathVariable final long id){
        Zipcode zipcode=zipcodeService.editZipcode(id,zipcodeRequestDto);
        return new ResponseEntity<>(zipcode,HttpStatus.OK);
    }

    @PostMapping("/addCity/{cityId}/toZipcode/{zipcodeId}")
    public ResponseEntity<Zipcode> addCity(@PathVariable final Long cityId,@PathVariable final Long zipcodeId){
        Zipcode zipcode=zipcodeService.addCityToZipcode(zipcodeId,cityId);
        return new ResponseEntity<>(zipcode,HttpStatus.OK);
    }

    @PostMapping("/deleteCity/{zipcodeId}")
    public ResponseEntity<Zipcode> deleteCity(@PathVariable final Long zipcodeId){
        Zipcode zipcode=zipcodeService.removeCityFromZipcode(zipcodeId);
        return new ResponseEntity<>(zipcode,HttpStatus.OK);
    }

}
