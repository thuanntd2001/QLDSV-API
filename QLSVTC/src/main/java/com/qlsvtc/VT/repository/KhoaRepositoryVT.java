package com.qlsvtc.VT.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qlsvtc.entity.Khoa;

public interface KhoaRepositoryVT extends JpaRepository<Khoa, String> {
    public Khoa findByMaKhoa(String makhoa);

}
