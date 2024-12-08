package com.qlsvtc.VT.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qlsvtc.entity.LopTinChi;

public interface LopTinChiRepositoryVT extends JpaRepository<LopTinChi, Integer> {
	public List<LopTinChi> findAllByMaNKHK(int maNKHK);

}
