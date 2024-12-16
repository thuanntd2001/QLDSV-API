package com.qlsvtc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "GIANGDAY")
public class GiangDay {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MAGIANGDAY", nullable = false)
	private int maGD;

	@Column(name = "MAGV")
	private String maGV;

	@Column(name = "MATB")
	private int maTB;

	@Column(name = "MALTC")
	private int maLTC;

	
}