package com.qlsvtc.model;

import lombok.Data;

@Data
public class WekaModel {
	public WekaModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	private int soTC;
	private String tyLeTH;
	private String maMH;

	private String kyThuat;

	private String phanTich;

	private String thietKe;
	private String kyNangNhom;

	private String diemCC;
	private String diemTK;

	private String chuyenNganh;

	public WekaModel(int soTC, String tyLeTH, String kyThuat, String phanTich, String thietKe, String kyNangNhom,
			String diemCC, String diemTK, String chuyenNganh) {
		super();
		this.soTC = soTC;
		this.tyLeTH = tyLeTH;
		this.kyThuat = kyThuat;
		this.phanTich = phanTich;
		this.thietKe = thietKe;
		this.kyNangNhom = kyNangNhom;
		this.diemCC = diemCC;
		this.diemTK = diemTK;
		this.chuyenNganh = chuyenNganh;
	}

}