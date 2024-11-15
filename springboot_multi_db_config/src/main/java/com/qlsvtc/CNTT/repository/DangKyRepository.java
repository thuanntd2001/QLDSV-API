package com.qlsvtc.CNTT.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qlsvtc.entity.DangKy;



public interface DangKyRepository extends JpaRepository<DangKy, Integer>{
    
    
    @Query(value = " EXECUTE SP_BO_DANG_KY :MASV,:MALTC",nativeQuery = true)
    Map<String, ?> huyDangKy(
            @Param("MASV") String maSV,
            @Param("MALTC") Integer maLTC);
}
