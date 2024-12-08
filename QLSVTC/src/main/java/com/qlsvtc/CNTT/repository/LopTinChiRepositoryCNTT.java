package com.qlsvtc.CNTT.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qlsvtc.entity.LopTinChi;

public interface LopTinChiRepositoryCNTT extends JpaRepository<LopTinChi, Integer> {
	public List<LopTinChi> findAllByMaNKHK(int maNKHK);

  
}
