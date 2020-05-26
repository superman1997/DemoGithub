package com.dangth.bhxh.model.location;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@EqualsAndHashCode
@Table(name = "city")
public class City {
    @Id
    private String idcity;
    private String city;
}
