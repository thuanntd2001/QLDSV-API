package com.qlsvtc.CNTT.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qlsvtc.entity.SinhVien;

public interface SinhVienRepositoryCNTT extends JpaRepository<SinhVien, String> {
    SinhVien findByMaSVAndPasswordAndDaNghiHoc(String maSV,String password,boolean daNghiHoc);

	List<SinhVien> findAllByLop_MaLopAndDaNghiHoc(String maLop, boolean b);


}
