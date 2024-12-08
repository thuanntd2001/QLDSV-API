package com.qlsvtc.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "NKHK") // Tên b?ng trong c? s? d? li?u
public class NKHK {

    @Id
    @Column(name = "MANKHK") // Tên c?t trong b?ng
    private int maNKHK;

    @Column(name = "MANV", nullable = false, length = 10) // Tên c?t và ?? dài
    private String maNV;

    @Column(name = "NAMHOC", nullable = false) // N?m h?c
    private int namHoc;

    @Column(name = "HK", nullable = false) // H?c k?
    private int hk;

    @Column(name = "NGAYMODK", nullable = false) // Ngày s?a ??i ??ng ký
    private LocalDate ngayMoDK;

    @Column(name = "NGAYDONGDK", nullable = false) // Ngày ?óng ??ng ký
    private LocalDate ngayDongDK;

}   