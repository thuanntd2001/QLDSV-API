package com.qlsvtc.CNTT.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.qlsvtc.entity.MonHoc;

public interface MonHocRepository extends JpaRepository<MonHoc, String> {
    MonHoc findBymamh(String maMh);

    @Query(value = "{call SP_GET_STC_MON_HOC(:tenmh)}", nativeQuery = true)
    public Map<String, ?> soTCMonHoc(@Param("tenmh") String tenMH);
    @Procedure(procedureName = "SP_THEM_MON_HOC")
    void themMonHoc(
            @Param("mamh") String mamh,
            @Param("tenmh") String tenmh,
            @Param("sotietlt") int sotietlt,
            @Param("sotietth") int sotietth,
            @Param("sotinchi") int sotinchi
    );
    @Procedure(procedureName = "SP_UPDATE_MON_HOC")
    void updateMonHoc(
            @Param("mamh") String mamh,
            @Param("tenmh") String tenmh,
            @Param("sotietlt") int sotietlt,
            @Param("sotietth") int sotietth,
            @Param("sotinchi") int sotinchi
    );
    @Procedure(procedureName = "SP_DELETE_MON_HOC")
    void xoaMonHoc(
            @Param("mamh") String mamh
    );
}