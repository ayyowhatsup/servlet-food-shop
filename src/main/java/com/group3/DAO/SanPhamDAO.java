package com.group3.DAO;

import java.sql.*;
import java.util.*;

import com.group3.Model.NguoiDung;
import com.group3.Model.SanPham;
import com.group3.Model.TheLoai;
import com.group3.TienIch.KetNoiCSDL;

/**
 * Các thao tác với Sản Phẩm với CSDL
 * 
 */
public class SanPhamDAO implements DAO<SanPham> {

	private Connection conn;
	public SanPhamDAO() {
		conn=KetNoiCSDL.ketNoiPostgreSQL();
	}
	public SanPhamDAO(Connection conn2) {
		conn = conn2;
	}
	@Override
	public List<SanPham> layTatCa() {
		ArrayList<SanPham> res = new ArrayList();
		try {
            Statement sql = conn.createStatement();
            ResultSet rs = sql.executeQuery("select * from SanPham");
            while (rs.next()) {
            	SanPham sp = new SanPham(rs);
            	TheLoai tl = sp.getTheLoai();
            	int id = tl.getMaTheLoai();
            	sp.setTheLoai(new TheLoaiDAO(conn).layQuaMa(id));
                res.add(sp);
            }

        } catch (SQLException ex) {
            
        }
        return res;
	}

	@Override
	public SanPham layQuaMa(int ma) {
		try {
			Statement sql = conn.createStatement();
			ResultSet rs = sql.executeQuery("Select * from SanPham where maSanPham = " + ma);
			rs.next();
			SanPham sp = new SanPham(rs);
			return sp;
		} catch (Exception e) {
			
		}
		return null;
	}

	@Override
	public int taoMoi(SanPham t) {
		String sql = "Insert into sanpham(tenSanPham,giaTien,mieuTa,hinhAnh,DonViTinh,TonKho,maTheLoai) values("
				+ "?,?,?,?,?,?,?)"
				+ "Returning maSanPham";
		try {
			PreparedStatement pps = conn.prepareStatement(sql);
			pps.setString(1,t.getTenSanPham());
			pps.setInt(2, t.getGiaTien());
			pps.setString(3,t.getMieuTa());
			pps.setString(4,t.getHinhAnh());
			pps.setString(5,t.getDonViTinh());
			pps.setInt(6, t.getTonKho());
			pps.setInt(7, t.getTheLoai().getMaTheLoai());
			ResultSet rs =  pps.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}

	@Override
	public void sua(SanPham t) {
		String sql = "UPDATE sanpham "
				+ "SET tenSanPham = ?, giaTien = ?, mieuTa = ?, hinhAnh = ?, DonViTinh = ?, TonKho = ?, maTheLoai = ? "
				+ "WHERE maSanPham = " + t.getMaSanPham();
		
		try {
			PreparedStatement pps = conn.prepareStatement(sql);
			pps.setString(1,t.getTenSanPham());
			pps.setInt(2, t.getGiaTien());
			pps.setString(3,t.getMieuTa());
			pps.setString(4,t.getHinhAnh());
			pps.setString(5,t.getDonViTinh());
			pps.setInt(6, t.getTonKho());
			pps.setInt(7, t.getTheLoai().getMaTheLoai());
			pps.execute();
		} catch (Exception e) {
			
		}
	}

	@Override
	public void xoa(SanPham t) {
		try {
			Statement stmt = conn.createStatement();
			stmt.execute("Delete from SanPham where maSanPham = " + t.getMaSanPham());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public List<SanPham> layTatCaSanPhamTheoMaTheLoai(int maTheLoai) {
		ArrayList<SanPham> res = new ArrayList();
		try {
			String sql = "SELECT * FROM SanPham WHERE maTheLoai = ?";
			PreparedStatement pps = conn.prepareStatement(sql);
			pps.setInt(1, maTheLoai);
			ResultSet rs =  pps.executeQuery();
            while (rs.next()) {
            	SanPham sp = new SanPham(rs);
            	//TheLoai tl = sp.getTheLoai();
            	//int id = tl.getMaTheLoai();
            	//sp.setTheLoai(new TheLoaiDAO(conn).layQuaMa(id));
                res.add(sp);
            }

        } catch (SQLException ex) {
            
        }
        return res;
	}
	public List<SanPham> timKiemSanPhamTheoTen(String tukhoa) {
		List<SanPham> danhSachSanPham = new ArrayList();
		try {
			Statement sql = conn.createStatement();
			
			ResultSet rs = sql.executeQuery("SELECT * FROM sanpham WHERE LOWER(tensanpham) LIKE '%" + tukhoa + "%'");
			while(rs.next()) {
				SanPham sp = new SanPham(rs);
				danhSachSanPham.add(sp);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return danhSachSanPham;
	}
}
