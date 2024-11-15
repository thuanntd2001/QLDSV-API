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

import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "LOPTINCHI")
public class LopTinChi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MALTC", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "NIENKHOA", nullable = false, length = 9)
    private String nienkhoa;

    @Column(name = "HOCKY", nullable = false)
    private Integer hocky;

    @Column(name = "NHOM", nullable = false)
    private Integer nhom;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MAKHOA", nullable = false)
    private Khoa makhoa;

    @Column(name = "SOSVTOITHIEU", nullable = false)
    private Integer sosvtoithieu;

    @Column(name = "HUYLOP", nullable = false)
    private Boolean huylop = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MALOP")
    private Lop malop;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNienkhoa() {
        return nienkhoa;
    }

    public void setNienkhoa(String nienkhoa) {
        this.nienkhoa = nienkhoa;
    }

    public Integer getHocky() {
        return hocky;
    }

    public void setHocky(Integer hocky) {
        this.hocky = hocky;
    }

    public Integer getNhom() {
        return nhom;
    }

    public void setNhom(Integer nhom) {
        this.nhom = nhom;
    }

    public Khoa getMakhoa() {
        return makhoa;
    }

    public void setMakhoa(Khoa makhoa) {
        this.makhoa = makhoa;
    }

    public Integer getSosvtoithieu() {
        return sosvtoithieu;
    }

    public void setSosvtoithieu(Integer sosvtoithieu) {
        this.sosvtoithieu = sosvtoithieu;
    }

    public Boolean getHuylop() {
        return huylop;
    }

    public void setHuylop(Boolean huylop) {
        this.huylop = huylop;
    }

    public Lop getMalop() {
        return malop;
    }

    public void setMalop(Lop malop) {
        this.malop = malop;
    }

}