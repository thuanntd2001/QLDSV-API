package com.qlsvtc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

import lombok.Data;

@Data
@Entity
@Table(name = "MONHOC")
public class MonHoc {
	@Id
	@Nationalized
	@Column(name = "MAMH", nullable = false, length = 10)

	private String mamh;

	@Nationalized
	@Column(name = "TENMH", nullable = false, length = 50)

	private String tenmh;

	@Column(name = "SOTIET_LT", nullable = false)

	private Integer sotietLt;

	@Column(name = "SOTIET_TH", nullable = false)
	private Integer sotietTh;

}