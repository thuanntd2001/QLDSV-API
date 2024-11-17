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
@Table(name = "DANGKY")
public class DangKy {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "MADK", nullable = false)
    private int maDK;

    @Column(name = "DIEM_CC")
    private Integer diemCc;

    @Column(name = "DIEM_GK")
    private Double diemGk;

    @Column(name = "DIEM_CK")
    private Double diemCk;

    @Column(name = "HUYDANGKY")
    private Boolean huydangky;
    

    @Column(name = "MALTC", nullable = false)
    private int maLTC;

    @Column(name = "MASV", nullable = false)
    private String maSV;
    
   
    
    

}