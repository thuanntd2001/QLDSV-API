package com.qlsvtc.DTO;


import lombok.Data;

@Data

public class SinhVienDTO {
  
    private String maSV;

   
    private String ho;

    
    private String ten;

    private Boolean phai = false;

    private String diaChi;

    private String ngaySinh;

   
    private String MaLop;


   
    private ChuyenNganhDTO CN;
    
    private Boolean daNghiHoc ;

    private String password ;
 

    
}