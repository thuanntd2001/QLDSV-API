package com.qlsvtc.model.baocao;

import lombok.Data;

@Data
public class BCPhieuDiem {
	private int stt;
	private String maMH;

	private String tenMonHoc;
	private float diemHetMon;
	private float diemCC;

	public String getDiemCC2() {
		if (diemCC <= 7)
			return "thap";
		else if (diemCC <= 8)
			return "TB";
		else
			return "cao";
	}

	public String getDiemTK2() {
		if (diemHetMon <= 6.5)
			return "thap";
		else if (diemHetMon <= 8)
			return "TB";
		else
			return "cao";
	}

}
