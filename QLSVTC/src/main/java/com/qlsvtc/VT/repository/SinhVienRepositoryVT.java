package com.qlsvtc.VT.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qlsvtc.entity.SinhVien;

public interface SinhVienRepositoryVT extends JpaRepository<SinhVien, String> {
    SinhVien findByMaSVAndPasswordAndDaNghiHoc(String maSV,String password,boolean daNghiHoc);


}
