package com.qlsvtc.CNTT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qlsvtc.entity.ChuyenNganh;

import java.util.List;
import java.util.Map;

public interface ChuyenNganhRepository extends JpaRepository<ChuyenNganh, Integer> {


    public ChuyenNganh findBytenCN(String tenNganh);
    @Query(value = "{call SP_FIND_LIST_NGANH()}", nativeQuery = true)
    List<Map<String,Object>> findDanhSachNganh();
}
