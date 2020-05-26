package com.dangth.bhxh.dto;

import com.dangth.bhxh.model.Address;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class WorkerDTO {
    private Long id;
    private Long identityId;
    private String fullName;
    private String birthDate;
    private Integer gender;
    private String identityNumber;
    private String place;
    private String validFrom;
    private String phoneNumber;
    private String email;
    private String msbh;
    private String salary;
    private String pc;
    private String ht;
    private Integer zone;
    private String workplace;
    private Address address;

    private String fee;
    private String type;
}
