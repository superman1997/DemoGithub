package com.dangth.bhxh.repository;

import com.dangth.bhxh.model.location.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProvinceRepository extends JpaRepository<Province, String> {
    List<Province> findAllByIdprovince(String idprovince);
    List<Province> findAllByIdcity(String id);

}
