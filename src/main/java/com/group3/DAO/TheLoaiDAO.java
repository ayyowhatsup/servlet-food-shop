package com.group3.DAO;

import java.sql.*;
import java.util.*;

import com.group3.Model.NguoiDung;
import com.group3.Model.TheLoai;
import com.group3.TienIch.KetNoiCSDL;

/**
 * Các thao tác với các Thể loại với CSDL
 * 
 */
public class TheLoaiDAO implements DAO<TheLoai> {

	private Connection conn;
	
	public TheLoaiDAO() {
		this.conn = KetNoiCSDL.ketNoiPostgreSQL();
	}
	public TheLoaiDAO(Connection conn) {
		this.conn = conn;
	}
	@Override
	public List<TheLoai> layTatCa() {
		ArrayList<TheLoai> res = new ArrayList();
		try {
            Statement sql = conn.createStatement();
            ResultSet rs = sql.executeQuery("select * from TheLoai");
            while (rs.next()) {
                TheLoai tl = new TheLoai(rs);
                res.add(tl);
            }

        } catch (SQLException ex) {
            
        }
        return res;
	}

	@Override
	public TheLoai layQuaMa(int ma) {
		
		try {
			TheLoai tl;
			Statement sql=conn.createStatement();
			ResultSet rs= sql.executeQuery("SELECT * FROM theloai WHERE maTheLoai = "+ma);
			rs.next();
			tl = new TheLoai(rs);
			return tl;
			
		} catch (SQLException e) {
			return null;
		}	
			
	}

	@Override
	public int taoMoi(TheLoai t) {
		try {
			String sql = "insert into TheLoai(tenTheLoai) values(?) RETURNING maTheLoai";
			PreparedStatement pps = conn.prepareStatement(sql);
			pps.setString(1, t.getTenTheLoai());
			ResultSet rs = pps.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (Exception e) {
			return -1;
		}
	}

	@Override
	public void sua(TheLoai t) {
		try {
			String sql = "UPDATE theloai SET tentheloai = ? WHERE matheloai = "+t.getMaTheLoai();
			PreparedStatement pps = conn.prepareStatement(sql);
			
			pps.setString(1, t.getTenTheLoai());
		
			pps.execute();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void xoa(TheLoai t) {
		try {
			Statement stmt = conn.createStatement();
			stmt.execute("DELETE FROM theloai WHERE maTheLoai = " + t.getMaTheLoai());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
