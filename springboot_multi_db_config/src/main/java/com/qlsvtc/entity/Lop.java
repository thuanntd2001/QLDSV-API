package com.qlsvtc.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

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
    @Column(name = "TRANGTHAI")
    private boolean trangThai;

    @Nationalized
    @Column(name = "KHOAHOC", nullable = false, length = 9)
    private String khoahoc;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "MAKHOA", nullable = false)
    private Khoa makhoa;



    public Lop() {
    }

    public String getMalop() {
        return malop;
    }

    public void setMalop(String malop) {
        this.malop = malop;
    }

    public String getTenlop() {
        return tenlop;
    }

    public void setTenlop(String tenlop) {
        this.tenlop = tenlop;
    }

    public String getKhoahoc() {
        return khoahoc;
    }

    public void setKhoahoc(String khoahoc) {
        this.khoahoc = khoahoc;
    }

    public Khoa getMakhoa() {
        return makhoa;
    }

    public void setMakhoa(Khoa makhoa) {
        this.makhoa = makhoa;
    }


    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "Lop{" +
                "malop='" + malop + '\'' +
                ", tenlop='" + tenlop + '\'' +
                ", trangThai=" + trangThai +
                ", khoahoc='" + khoahoc + '\'' +
                ", makhoa=" + makhoa.toString() +
                
                '}';
    }
}