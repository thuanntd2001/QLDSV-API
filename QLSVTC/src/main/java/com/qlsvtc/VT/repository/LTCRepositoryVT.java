package com.qlsvtc.VT.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qlsvtc.entity.LTC;

public interface LTCRepositoryVT extends JpaRepository<LTC, Integer> {
	public List<LTC> findAllByMaNKHKAndHuyLop(int maNKHK,boolean huyLop);

}
