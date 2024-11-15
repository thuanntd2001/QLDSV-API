package com.qlsvtc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "CHUYENNGANH")
@Data
public class ChuyenNganh {
    @Id
    @Column(name = "MACN", nullable = false)
    private String maCN;

 
    @Column(name = "TENCN", nullable = false, length = 50)
    private String tenCN;


    @Column(name = "MAKHOA", nullable = false, length = 50)
    private String maKhoa;
}