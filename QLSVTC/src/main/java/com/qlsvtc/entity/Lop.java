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
    private String malop;

    @Nationalized
    @Column(name = "TENLOP", nullable = false, length = 50)
    private String tenlop;

    @Nationalized
    @Column(name = "KHOAHOC", nullable = false, length = 9)
    private int khoahoc;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MAKHOA", nullable = false)
    private Khoa makhoa;



  
}