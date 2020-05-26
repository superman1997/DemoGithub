package com.dangth.bhxh.controller;

import com.dangth.bhxh.model.location.City;
import com.dangth.bhxh.model.location.Commune;
import com.dangth.bhxh.model.location.Hamlet;
import com.dangth.bhxh.model.location.Province;
import com.dangth.bhxh.repository.CityRepository;
import com.dangth.bhxh.repository.CommuneRepository;
import com.dangth.bhxh.repository.HamletRepository;
import com.dangth.bhxh.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressBookController {

    private final ProvinceRepository provinceRepository;
    private final HamletRepository hamletRepository;
    private final CommuneRepository communeRepository;
    private final CityRepository cityRepository;

    @Autowired
    public AddressBookController(ProvinceRepository provinceRepository, HamletRepository hamletRepository, CommuneRepository communeRepository, CityRepository cityRepository) {
        this.provinceRepository = provinceRepository;
        this.hamletRepository = hamletRepository;
        this.communeRepository = communeRepository;
        this.cityRepository = cityRepository;
    }

    @GetMapping("/province/{id}")
    public List<Province> getProvince(@PathVariable String id) {
        return provinceRepository.findAllByIdcity(id);
    }
    @GetMapping("/commune/{id}")
    public List<Commune> getCommune(@PathVariable String id) {
        return communeRepository.findAllByIdprovince(id);
    }

    @GetMapping("/hamlet/{id}")
    public List<Hamlet> getHamlet(@PathVariable String id) {
        return hamletRepository.findAllByIdcommune(id);
    }
    @GetMapping("/city/{id}")
    public City getCity(@PathVariable String id) {
        return cityRepository.findById(id).orElse(new City());
    }
}
