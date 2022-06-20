package com.group3.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.annotations.Expose;

public class SanPham {
	private int maSanPham;
	private String tenSanPham;
	private int giaTien;
	private String mieuTa;
	private String hinhAnh;
	private String donViTinh;
	private int tonKho;
	private TheLoai theLoai;
	
	
	public SanPham() {
		
	}
	
	
	public SanPham(ResultSet rs) throws SQLException {
		maSanPham = rs.getInt(1);
		tenSanPham = rs.getString(2);
		giaTien = rs.getInt(3);
		mieuTa = rs.getString(4);
		hinhAnh =  rs.getString(5);
		donViTinh = rs.getString(6);
		tonKho = rs.getInt(7);
		theLoai = new TheLoai();
		theLoai.setMaTheLoai(rs.getInt(8));
	}
	
	@Override
	public String toString() {
		return "SanPham [maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", giaTien=" + giaTien + ", mieuTa="
				+ mieuTa + ", hinhAnh=" + hinhAnh + ", donViTinh=" + donViTinh + ", tonKho=" + tonKho + ", theLoai="
				+ theLoai + "]";
	}


	public String getHinhAnh() {
		return hinhAnh;
	}



	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}



	public String getDonViTinh() {
		return donViTinh;
	}



	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}



	public TheLoai getTheLoai() {
		return theLoai;
	}



	public void setTheLoai(TheLoai theLoai) {
		this.theLoai = theLoai;
	}



	

	public int getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(int maSanPham) {
		this.maSanPham = maSanPham;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public int getGiaTien() {
		return giaTien;
	}

	public void setGiaTien(int giaTien) {
		this.giaTien = giaTien;
	}

	public String getMieuTa() {
		return mieuTa;
	}

	public void setMieuTa(String mieuTa) {
		this.mieuTa = mieuTa;
	}

	public int getTonKho() {
		return tonKho;
	}

	public void setTonKho(int tonKho) {
		this.tonKho = tonKho;
	}
	
	
	
}
