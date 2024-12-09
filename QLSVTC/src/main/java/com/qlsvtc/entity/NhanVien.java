package com.qlsvtc.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

import lombok.Data;

@Data
@Entity
@Table(name = "NHANVIEN")
public class NhanVien {
    @Id
    @Column(name = "MANV", nullable = false, length = 10)
    private String maNV;

    
   
    @Nationalized
    @Column(name = "TENNV", nullable = false, length = 10)
    private String tenNV;

   

}