package com.group3.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.group3.Model.DonHangChiTiet;
import com.group3.Model.NguoiDung;
import com.group3.TienIch.KetNoiCSDL;

/**
 * Các thao tác với DonHangChiTiet với CSDL
 * 
 */
public class DonHangChiTietDAO implements DAO<DonHangChiTiet> {
	
	private Connection conn;

	public DonHangChiTietDAO() {
		conn = KetNoiCSDL.ketNoiPostgreSQL();
	}
	public DonHangChiTietDAO(Connection conn) {
		this.conn = conn;
	}
	@Override
	public List<DonHangChiTiet> layTatCa() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DonHangChiTiet layQuaMa(int ma) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int taoMoi(DonHangChiTiet t) {
		try {
            String sql = "insert into DonHangChiTiet(maDonHang,maSanPham,soLuong)\nvalues(?,?,?) RETURNING maDonHangChiTiet";
            PreparedStatement pps = conn.prepareStatement(sql);
            pps.setInt(1,t.getMaDonHang());
            pps.setInt(2,t.getSanPham().getMaSanPham());
            pps.setInt(3, t.getSoLuong());
            ResultSet rs =  pps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            return -1;
        }
	}

	@Override
	public void sua(DonHangChiTiet t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void xoa(DonHangChiTiet t) {
		// TODO Auto-generated method stub
		
	}
	
	public List<DonHangChiTiet> layDanhSachVatPhamHoaDonTheoMa(int maHoaDon){
		List<DonHangChiTiet> res = new ArrayList<DonHangChiTiet>();
		SanPhamDAO spd = new SanPhamDAO(conn);
		try {
            Statement sql = conn.createStatement();
            ResultSet rs = sql.executeQuery("select * from DonHangChiTiet where maDonHang = " + maHoaDon);
            while (rs.next()) {
                DonHangChiTiet dhct = new DonHangChiTiet(rs);
                int maSP = dhct.getSanPham().getMaSanPham();
                dhct.setSanPham(spd.layQuaMa(maSP));
                res.add(dhct);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return res;
	}

}
