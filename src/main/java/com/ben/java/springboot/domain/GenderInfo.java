package com.ben.java.springboot.domain;

import javax.persistence.*;

@Entity
@Table(name = "sys_gender_dict")
public class GenderInfo {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genderId;
    @Column
    private String gender;
}
