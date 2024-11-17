package com.qlsvtc.CNTT.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.qlsvtc.entity.GiangVien;

import java.util.List;
import java.util.Map;

public interface GiangVienRepositoryCNTT extends JpaRepository<GiangVien, String> {
    @Query(value = "{call SP_THONG_TIN_GV(:magv)}", nativeQuery = true)
    public Map<String, ?> thongTinCaNhanGiangVien(@Param("magv") String magv);

    GiangVien findBymagv(String magv);
    List<GiangVien> findAll();
    @Procedure(procedureName = "SP_THEM_GV_KHOA")
    void themGiangVienMoi(
            @Param("magv") String magv,
            @Param("ho") String ho,
            @Param("ten") String ten,
            @Param("hocham") String hocham,
            @Param("hocvi") String hocvi,
            @Param("chuyenmon") String chuyenmon,
            @Param("sdt") String sdt,
            @Param("hinhanh") String hinhanh,
            @Param("email") String email,
            @Param("makhoa") String makhoa,
            @Param("password") String password
            );
    @Procedure (procedureName = "SP_DELETE_GV")
    void xoaGiangVien(
            @Param("magv") String magv
    );
    @Query(value = "EXEC SP_LOC_MA_KHOA", nativeQuery = true)
    List<String> locMaKhoa();
    @Procedure (procedureName = "SP_UPDATE_INFO_GV")
    void updateGiangVien(
            @Param("magv") String magv,
            @Param("ho") String ho,
            @Param("ten") String ten,
            @Param("hocham") String hocham,
            @Param("hocvi") String hocvi,
            @Param("chuyenmon") String chuyenmon,
            @Param("sdt") String sdt,
            @Param("hinhanh") String hinhanh,
            @Param("email") String email,
            @Param("makhoa") String makhoa
    );


    @Query(value = "EXEC SP_LOC_DANH_SACH_GIANG_VIEN :makhoa", nativeQuery = true)
    List<GiangVien> danhSachGiangVien(@Param("makhoa") String makhoa);


    @Query(value = "EXEC SP_TIM_GIANG_VIEN :magv", nativeQuery = true)
    GiangVien timGiangVien(@Param("magv") String magv);


}
