package com.group3.Model;

import java.io.Serializable;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DonHang implements Serializable{
	private int maDonHang;
	private Date thoiGianDatHang;
	private NguoiDung khachHang;
	private int thanhTien;
	private String trangThai;
	private List<DonHangChiTiet> danhSachVatPham;
	private String diaChiNhanHang;
	
	
	
	@Override
	public String toString() {
		return "DonHang [maDonHang=" + maDonHang + ", thoiGianDatHang=" + thoiGianDatHang + ", khachHang=" + khachHang
				+ ", thanhTien=" + thanhTien + ", trangThai=" + trangThai + ", danhSachVatPham=" + danhSachVatPham
				+ ", diaChiNhanHang=" + diaChiNhanHang + "]";
	}

	public String getDiaChiNhanHang() {
		return diaChiNhanHang;
	}

	public void setDiaChiNhanHang(String diaChiNhanHang) {
		this.diaChiNhanHang = diaChiNhanHang;
	}

	public DonHang() {
		
	}

	public DonHang(ResultSet rs) throws SQLException {
		maDonHang = rs.getInt(1);
		khachHang = new NguoiDung();
		khachHang.setMaNguoiDung(rs.getInt(2));
		thoiGianDatHang = rs.getTimestamp(3);
		thanhTien = rs.getInt(4);
		trangThai = rs.getString(5);
		diaChiNhanHang = rs.getString(6);
	} 

	public int getMaDonHang() {
		return maDonHang;
	}

	public void setMaDonHang(int maDonHang) {
		this.maDonHang = maDonHang;
	}

	public Date getThoiGianDatHang() {
		return thoiGianDatHang;
	}

	public void setThoiGianDatHang(Date thoiGianDatHang) {
		this.thoiGianDatHang = thoiGianDatHang;
	}

	public NguoiDung getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(NguoiDung khachHang) {
		this.khachHang = khachHang;
	}

	public int getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(int thanhTien) {
		this.thanhTien = thanhTien;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public List<DonHangChiTiet> getDanhSachVatPham() {
		return danhSachVatPham;
	}

	public void setDanhSachVatPham(List<DonHangChiTiet> danhSachVatPham) {
		this.danhSachVatPham = danhSachVatPham;
	}

	public void tinhTongTien() {
		int sum=0;
		for(DonHangChiTiet dhct : danhSachVatPham) {
			sum+=(dhct.getSanPham().getGiaTien()*dhct.getSoLuong());
		}
		thanhTien = sum;
	}

}
