package com.qlsvtc.CNTT.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.qlsvtc.entity.LopTinChi;

public interface LopTinChiRepositoryCNTT extends JpaRepository<LopTinChi, Integer> {
  
}
