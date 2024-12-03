package com.qlsvtc.model;

import lombok.Data;
import java.util.Map;

@Data
public class BCBangDiemTongKetMap {
    private Map<String, Map<String, Float>> ten_monHoc_diem; 
}