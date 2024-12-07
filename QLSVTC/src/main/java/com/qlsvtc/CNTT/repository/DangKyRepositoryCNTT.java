package com.qlsvtc.CNTT.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qlsvtc.entity.DangKy;



public interface DangKyRepositoryCNTT extends JpaRepository<DangKy, Integer>{
    
    

}
