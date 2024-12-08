package com.qlsvtc.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "LOPTINCHI")
public class LopTinChi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MALTC", nullable = false)
    private Integer maLTC;

    @Column(name = "MANKHK", nullable = false)
    private Integer maNKHK;

    @Column(name = "NHOM", nullable = false)
    private Integer nhom;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MAKHOA", nullable = false)
    private Khoa khoa;

    @Column(name = "SOSVTOITHIEU", nullable = false)
    private Integer soSVToiThieu;

    @Column(name = "HUYLOP", nullable = false)
    private Boolean huyLop = false;

    @Column(name = "MAMH", nullable = false)
    private String maMH;

    

}