package com.group3.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.group3.Model.DonHang;
import com.group3.Model.DonHangChiTiet;
import com.group3.Model.NguoiDung;
import com.group3.TienIch.KetNoiCSDL;

/**
 * Các thao tác với DonHang với CSDL
 * 
 */
public class DonHangDAO implements DAO<DonHang> {
	
	private Connection conn;
	
	public DonHangDAO() {
		conn = KetNoiCSDL.ketNoiPostgreSQL();
	}

	public DonHangDAO(Connection conn) {
		this.conn = conn;
	}
	@Override
	public List<DonHang> layTatCa() {
		ArrayList<DonHang> res = new ArrayList();
		try {
            Statement sql = conn.createStatement();
            ResultSet rs = sql.executeQuery("select * from DonHang");
            while (rs.next()) {
                DonHang dh = new DonHang(rs);
                dh.setKhachHang(new NguoiDungDAO(conn).layQuaMa(dh.getKhachHang().getMaNguoiDung()));
                dh.setDanhSachVatPham(new DonHangChiTietDAO(conn).layDanhSachVatPhamHoaDonTheoMa(dh.getMaDonHang()));
                res.add(dh);
            }

        } catch (SQLException ex) {
            
        }
        return res;
	}

	@Override
	public DonHang layQuaMa(int ma) {
		
		try {
			DonHang dh = new DonHang();
			String sql = "Select * from DonHang where maDonHang = ?";
			PreparedStatement pps = conn.prepareStatement(sql);
			pps.setInt(1, ma);
			ResultSet rs =  pps.executeQuery();
			rs.next();
			dh = new DonHang(rs);
			NguoiDung nd =new NguoiDungDAO(conn).layQuaMa(dh.getKhachHang().getMaNguoiDung());
			dh.setKhachHang(nd);
			List<DonHangChiTiet> arr = new DonHangChiTietDAO(conn).layDanhSachVatPhamHoaDonTheoMa(ma);
			dh.setDanhSachVatPham(arr);	
			return dh;
		} catch (Exception e) {
			
		}
		
		return null;
	}

	@Override
	public int taoMoi(DonHang t) {
		try {
			
            String sql = "insert into DonHang(manguoidung,thoigiandathang,thanhtien,trangthai,diachinhanhang)\nvalues(?,?,?,?,?) RETURNING maDonHang";
            PreparedStatement pps = conn.prepareStatement(sql);
            pps.setInt(1,t.getKhachHang().getMaNguoiDung());
            pps.setTimestamp(2, new Timestamp(t.getThoiGianDatHang().getTime()));
            pps.setInt(3, t.getThanhTien());
            pps.setString(4, t.getTrangThai());
            pps.setString(5,t.getDiaChiNhanHang());
            ResultSet rs =  pps.executeQuery();
            rs.next();
            int  maDonHang = rs.getInt(1);         
            DonHangChiTietDAO dhctdao = new DonHangChiTietDAO(conn);
            t.setMaDonHang(maDonHang);
            for(DonHangChiTiet dhct : t.getDanhSachVatPham()) {
            	dhct.setMaDonHang(t.getMaDonHang());
            	dhctdao.taoMoi(dhct);
            }
            return maDonHang;
        } catch (SQLException ex) {
            return -1;
        }
	}

	@Override
	public void sua(DonHang t) {

		
		
		
	}
public void suaTrangThai(DonHang t) {

		
		String sql = "UPDATE donhang "
				+ "set trangThai= ? "
				+ "WHERE maDonHang = " + t.getMaDonHang();
		
		try {
			PreparedStatement pps = conn.prepareStatement(sql);
			pps.setString(1,t.getTrangThai());
			pps.execute();
		} catch (SQLException e) {
			
		}
		
	}
	

	@Override
	public void xoa(DonHang t) {
		try {
			Statement stmt=conn.createStatement();
		stmt.executeQuery("delete from donhang where maDonHang = "+t.getMaDonHang());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public List<DonHang> layThongTinDonHangTheoMaNguoiDung(int maNguoiDung){
		//Không lấy các sản phẩm trong đơn hàng
		ArrayList<DonHang> arr = new ArrayList<DonHang>();
		try {
			String sql = "Select * from DonHang where maNguoiDung = ?";
			PreparedStatement pps = conn.prepareStatement(sql);
			pps.setInt(1, maNguoiDung);
			ResultSet rs =  pps.executeQuery();
			while(rs.next()) {
				DonHang dh = new DonHang(rs);
				arr.add(dh);
			} 
		} catch (Exception e) {
			
		}
		
		return arr;
		
	}

}
