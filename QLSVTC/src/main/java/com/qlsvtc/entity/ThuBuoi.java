package com.qlsvtc.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "THUBUOI")
public class ThuBuoi {
    @Id
    @Column(name = "MATHUBUOI", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maTB;

    @Column(name = "THU", nullable = false)
    private int thu;

    @Column(name = "BUOI", nullable = false)
    private int buoi;

}