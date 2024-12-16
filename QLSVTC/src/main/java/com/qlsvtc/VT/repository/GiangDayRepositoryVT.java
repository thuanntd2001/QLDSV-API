package com.qlsvtc.VT.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qlsvtc.entity.GiangDay;



public interface GiangDayRepositoryVT extends JpaRepository<GiangDay, Integer>{
    
    List<GiangDay> findAllByMaLTC(int maLTC);


}
