package com.group3.Model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DonHangChiTiet implements Serializable {
	
	private int maDonHangChiTiet;
	private SanPham sanPham;
	private int soLuong;
	private int maDonHang;
	
	public DonHangChiTiet(ResultSet rs) throws SQLException{
		maDonHangChiTiet = rs.getInt(1);
		maDonHang = rs.getInt(2);
		sanPham = new SanPham();
		sanPham.setMaSanPham(rs.getInt(3));
		soLuong = rs.getInt(4);
	}

	public int getMaDonHangChiTiet() {
		return maDonHangChiTiet;
	}

	public void setMaDonHangChiTiet(int maDonHangChiTiet) {
		this.maDonHangChiTiet = maDonHangChiTiet;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public int getMaDonHang() {
		return maDonHang;
	}

	public void setMaDonHang(int maDonHang) {
		this.maDonHang = maDonHang;
	}

	public DonHangChiTiet() {
		// TODO Auto-generated constructor stub
	}
}
