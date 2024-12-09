package com.qlsvtc.CNTT.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qlsvtc.entity.SinhVien;

public interface SinhVienRepositoryCNTT extends JpaRepository<SinhVien, String> {
	SinhVien findByMaSVAndPasswordAndDaNghiHoc(String maSV, String password, boolean daNghiHoc);

	List<SinhVien> findAllByLop_MaLopAndDaNghiHoc(String maLop, boolean b);

	@Query(value = "CALL SP_THEM_SV_2(:maLop, :maCN, :ho, :ten, :phai, :diaChi, :ngaySinh, :daNghiHoc, :password)", nativeQuery = true)
	String spThemSV(@Param("maLop") String maLop, @Param("maCN") String maCN, @Param("ho") String ho,
			@Param("ten") String ten, @Param("phai") boolean phai, @Param("diaChi") String diaChi,
			@Param("ngaySinh") LocalDate ngaySinh, @Param("daNghiHoc") boolean daNghiHoc,
			@Param("password") String password);
}
