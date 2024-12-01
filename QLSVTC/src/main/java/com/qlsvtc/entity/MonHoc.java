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

	private String maMH;

	@Nationalized
	@Column(name = "TENMONHOC", nullable = false, length = 50)

	private String tenMH;

	@Column(name = "SOTIETLT", nullable = false)

	private Integer sotietLT;

	@Column(name = "SOTIETTH", nullable = false)
	private Integer sotietTH;

}