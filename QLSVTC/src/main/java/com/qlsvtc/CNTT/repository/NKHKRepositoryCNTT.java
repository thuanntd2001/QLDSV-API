package com.qlsvtc.CNTT.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qlsvtc.entity.NKHK;

public interface NKHKRepositoryCNTT extends JpaRepository<NKHK, Integer> {

	public NKHK findByMaNKHK(int maNKHK);

}
