package com.qlsvtc.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "KHOA")
public class Khoa {
    @Id
    @Column(name = "MAKHOA", nullable = false, length = 10)
    private String maKhoa;

    @Column(name = "TENKHOA", nullable = false, length = 50)
    private String tenKhoa;

    @Column(name = "MATRUONGKHOA", nullable = false, length = 50)
    private String maTruongKhoa;

}