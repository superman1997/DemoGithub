package com.dangth.bhxh.repository;

import com.dangth.bhxh.model.location.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, String> {
    City findByCity(String name);
}
