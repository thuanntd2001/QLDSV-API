package com.qlsvtc.VT.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qlsvtc.entity.NKHK;

public interface NKHKRepositoryVT extends JpaRepository<NKHK, Integer> {

	public NKHK findByMaNKHK(int maNKHK);


}
