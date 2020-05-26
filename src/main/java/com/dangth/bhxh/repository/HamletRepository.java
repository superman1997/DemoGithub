package com.dangth.bhxh.repository;

import com.dangth.bhxh.model.location.Hamlet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HamletRepository extends CrudRepository<Hamlet, String> {
    List<Hamlet> findAllByIdcommune(String id);
}
