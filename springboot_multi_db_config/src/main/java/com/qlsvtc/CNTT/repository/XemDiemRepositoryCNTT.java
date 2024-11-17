package com.qlsvtc.CNTT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qlsvtc.entity.LopTinChi;

import java.util.List;
import java.util.Map;

public interface XemDiemRepositoryCNTT extends JpaRepository<LopTinChi, Integer> {

    @Query(value = "{call SP_GET_DANH_SACH_NAM_HOC(:masv)}", nativeQuery = true)
    public List<String> danhSachNamHoc(@Param("masv") String masv);

    @Query(value = "{call SP_GET_DANH_SACH_HOC_KI(:masv, :nienkhoa)}", nativeQuery = true)
    public List<String> danhSachHocKi(@Param("masv") String masv, @Param("nienkhoa") String nienKhoa);

    @Query(value = "{call SP_XEM_DIEM_SINH_VIEN(:masv, :nienkhoa, :hocki)}", nativeQuery = true)
    public List<Map<String, ?>> danhSachDiemHK(@Param("masv") String masv, @Param("nienkhoa") String nienKhoa, @Param("hocki") int hocKi);
}
