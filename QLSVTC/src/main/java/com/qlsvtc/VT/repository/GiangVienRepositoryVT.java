package com.qlsvtc.VT.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.qlsvtc.entity.GiangVien;

import java.util.List;
import java.util.Map;

public interface GiangVienRepositoryVT extends JpaRepository<GiangVien, String> {
  

}
