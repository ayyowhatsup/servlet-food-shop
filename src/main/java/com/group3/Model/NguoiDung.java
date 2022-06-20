package com.group3.Model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NguoiDung implements Serializable{
	private int maNguoiDung;
	private String tenNguoiDung;
	private String soDienThoai;
	private String matKhauMaHoa;
	private int laQuanTriVien;

	public NguoiDung() {

	}

	public NguoiDung(ResultSet rs) throws SQLException {
		maNguoiDung = rs.getInt(1);
		tenNguoiDung = rs.getString(2);
		soDienThoai = rs.getString(3);
		matKhauMaHoa = rs.getString(4);
		laQuanTriVien = rs.getInt(5);
	}

	public NguoiDung(String tenNguoiDung2, String soDienThoai2, String matKhauMaHoa2) {
		tenNguoiDung = tenNguoiDung2;
		soDienThoai = soDienThoai2;
		matKhauMaHoa = matKhauMaHoa2;
		laQuanTriVien = 0;
	}

	public int getLaQuanTriVien() {
		return laQuanTriVien;
	}

	public void setLaQuanTriVien(int laQuanTriVien) {
		this.laQuanTriVien = laQuanTriVien;
	}

	public int getMaNguoiDung() {
		return maNguoiDung;
	}

	public void setMaNguoiDung(int maNguoiDung) {
		this.maNguoiDung = maNguoiDung;
	}

	public String getTenNguoiDung() {
		return tenNguoiDung;
	}

	public void setTenNguoiDung(String tenNguoiDung) {
		this.tenNguoiDung = tenNguoiDung;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}


	public String getMatKhauMaHoa() {
		return matKhauMaHoa;
	}

	public void setMatKhauMaHoa(String matKhauMaHoa) {
		this.matKhauMaHoa = matKhauMaHoa;
	}

}
