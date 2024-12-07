package com.qlsvtc.CNTT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qlsvtc.entity.Khoa;

public interface KhoaRepositoryCNTT extends JpaRepository<Khoa, String> {
    public Khoa findByMaKhoa(String makhoa);

  
}
