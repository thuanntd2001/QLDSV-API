package com.qlsvtc.DTO;


import java.time.LocalDate;

import lombok.Data;

@Data

public class SinhVienDTO {
  
    private String maSV;

   
    private String ho;

    
    private String ten;

    private Boolean phai = false;

    private String diaChi;

    private LocalDate ngaySinh;

   
    private String maLop;


   
    private String maCN;
    private String tenCN;

    
    private Boolean daNghiHoc ;

    private String password ;
 

    
}