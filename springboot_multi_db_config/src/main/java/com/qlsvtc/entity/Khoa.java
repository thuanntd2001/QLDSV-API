package com.qlsvtc.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "KHOA")
public class Khoa {
    @Id
    @Column(name = "MAKHOA", nullable = false, length = 10)
    private String makhoa;

    @Nationalized
    @Column(name = "TENKHOA", nullable = false, length = 50)
    private String tenkhoa;

    public String getMakhoa() {
        return makhoa;
    }

    public void setMakhoa(String makhoa) {
        this.makhoa = makhoa;
    }

    public String getTenkhoa() {
        return tenkhoa;
    }

    public void setTenkhoa(String tenkhoa) {
        this.tenkhoa = tenkhoa;
    }

}