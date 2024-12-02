package com.qlsvtc.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

import lombok.Data;

@Data
@Entity
@Table(name = "SINHVIEN")
public class SinhVien {
    @Id
    @Nationalized
    @Column(name = "MASV", nullable = false, length = 10)
    private String maSV;

    @Nationalized
    @Column(name = "HO", nullable = false, length = 50)
    private String ho;

    @Nationalized
    @Column(name = "TEN", nullable = false, length = 10)
    private String ten;

    @Column(name = "PHAI", nullable = false)
    private Boolean phai = false;

    @Nationalized
    @Column(name = "DIACHI", length = 100)
    private String diaChi;

    @Column(name = "NGAYSINH")
    private String ngaySinh;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MALOP", nullable = false)
    private Lop lop;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MACN", nullable = false)
    private ChuyenNganh CN;
    
    @Column(name = "DANGHIHOC", nullable = false)
    private Boolean daNghiHoc ;

    @Column(name = "PASSWORD", nullable = true)
    private String password ;
 

    
}