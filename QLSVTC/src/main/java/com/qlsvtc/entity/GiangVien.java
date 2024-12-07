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
@Table(name = "GIANGVIEN")
public class GiangVien {
    @Id
    @Column(name = "MAGV", nullable = false, length = 10)
    private String maGV;

    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MAKHOA", nullable = false)
    private Khoa khoa;


    @Nationalized
    @Column(name = "HO", nullable = false, length = 50)
    private String ho;

    @Nationalized
    @Column(name = "TEN", nullable = false, length = 10)
    private String ten;

    @Nationalized
    @Column(name = "HOCVI", length = 20)
    private String hocVi;

    @Nationalized
    @Column(name = "HOCHAM", length = 20)
    private String hocHam;

    @Nationalized
    @Column(name = "CHUYENMON", length = 50)
    private String chuyenMon;




    


}