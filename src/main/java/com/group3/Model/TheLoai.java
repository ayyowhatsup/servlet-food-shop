package com.group3.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.annotations.Expose;

public class TheLoai {
	private int maTheLoai;
	private String tenTheLoai;
	
	public TheLoai() {}
	
	public TheLoai(ResultSet rs)  throws SQLException {
		maTheLoai = rs.getInt(1);
		tenTheLoai = rs.getString(2);
	}

	public int getMaTheLoai() {
		return maTheLoai;
	}

	@Override
	public String toString() {
		return "TheLoai [maTheLoai=" + maTheLoai + ", tenTheLoai=" + tenTheLoai + "]";
	}

	public void setMaTheLoai(int maTheLoai) {
		this.maTheLoai = maTheLoai;
	}

	public String getTenTheLoai() {
		return tenTheLoai;
	}

	public void setTenTheLoai(String tenTheLoai) {
		this.tenTheLoai = tenTheLoai;
	}
	
	
	
}
