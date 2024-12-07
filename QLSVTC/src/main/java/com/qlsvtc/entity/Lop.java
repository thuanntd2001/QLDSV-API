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
@Table(name = "LOP")
public class Lop {
    @Id
    @Nationalized
    @Column(name = "MALOP", nullable = false, length = 10)
    private String maLop;

    @Nationalized
    @Column(name = "TENLOP", nullable = false, length = 50)
    private String tenLop;

    @Nationalized
    @Column(name = "KHOAHOC", nullable = false, length = 9)
    private int khoaHoc;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MAKHOA", nullable = false)
    private Khoa khoa;



  
}