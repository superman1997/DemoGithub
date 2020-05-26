package com.dangth.bhxh.model.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "hamlet")
public class Hamlet {
    @Id
    private String idhamlet;
    private String idcommune;
    private String hamlet;
}
