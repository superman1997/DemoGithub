package com.dangth.bhxh.repository;

import com.dangth.bhxh.model.location.Commune;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommuneRepository extends CrudRepository<Commune, String> {
    List<Commune> findAllByIdprovince(String id);
}
