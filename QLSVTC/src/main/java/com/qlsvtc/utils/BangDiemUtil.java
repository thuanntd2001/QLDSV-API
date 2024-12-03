package com.qlsvtc.utils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.qlsvtc.model.UniqueMHSVModel;
import com.qlsvtc.model.baocao.BCBangDiemTongKet;

public class BangDiemUtil {

    public static UniqueMHSVModel getUniqueTenMonHoc_SV(List<BCBangDiemTongKet> bangDiemList) {
    	UniqueMHSVModel kq= new UniqueMHSVModel();
    	Set<String> uniqueTenMonHoc = new HashSet<>();
    	Set<String> uniqueTenSV = new HashSet<>();

        for (BCBangDiemTongKet bangDiem : bangDiemList) {
            uniqueTenMonHoc.add(bangDiem.getTenMH());
            uniqueTenSV.add(bangDiem.getMaSVHoTen());
        }
        kq.setTenMHs(uniqueTenMonHoc.stream().collect(Collectors.toList()));
        kq.setTenSVs(uniqueTenSV.stream().collect(Collectors.toList()));

        return kq;
    }
    public static List<String> getUniqueTenMonHoc(List<BCBangDiemTongKet> bangDiemList) {
    	Set<String> uniqueTenMonHoc = new HashSet<>();

        for (BCBangDiemTongKet bangDiem : bangDiemList) {
            uniqueTenMonHoc.add(bangDiem.getTenMH());
        }
      

        return uniqueTenMonHoc.stream().collect(Collectors.toList());
    }
    public static Map<String, Map<String, Float>> convertToMap(List<BCBangDiemTongKet> bangDiemList) {
        Map<String, Map<String, Float>> tenMonHocDiem = new HashMap<>();

        for (BCBangDiemTongKet bangDiem : bangDiemList) {
            String maSVHoTen = bangDiem.getMaSVHoTen();
            String tenMH = bangDiem.getTenMH();
            float diemSV = bangDiem.getDiemSV();

            tenMonHocDiem.putIfAbsent(maSVHoTen, new HashMap<>());

            tenMonHocDiem.get(maSVHoTen).put(tenMH, diemSV);
        }

        return tenMonHocDiem;
    }
}

