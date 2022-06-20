package com.group3.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.group3.Model.NguoiDung;
import com.group3.TienIch.KetNoiCSDL;

/**
 * Các thao tác với Người Dùng với CSDL
 * 
 */
public class NguoiDungDAO implements DAO<NguoiDung> {
	
	private Connection conn;
	
	public NguoiDungDAO() {
		conn = KetNoiCSDL.ketNoiPostgreSQL();
	}
	public NguoiDungDAO(Connection conn2) {
		conn = conn2;
	}
	@Override
	public List<NguoiDung> layTatCa() {
		ArrayList<NguoiDung> res = new ArrayList();
		try {
            Statement sql = conn.createStatement();
            ResultSet rs = sql.executeQuery("select * from NguoiDung");
            while (rs.next()) {
                NguoiDung nd = new NguoiDung(rs);
                res.add(nd);
            }

        } catch (SQLException ex) {
            
        }
        return res;
	}

	@Override
	public NguoiDung layQuaMa(int ma) {
		try {
			Statement sql = conn.createStatement();
			ResultSet rs = sql.executeQuery("Select * from NguoiDung where maNguoiDung = " + ma);
			rs.next();
			NguoiDung nd = new NguoiDung(rs);
			return nd;
		} catch (Exception e) {
			
		}
		return null;
	}

	@Override
	public int taoMoi(NguoiDung t) {
        try {
            String sql = "insert into NguoiDung(tennguoidung,sodienthoai,matkhaumahoa,laquantrivien)\nvalues(?,?,?,?) RETURNING maNguoiDung";
            PreparedStatement pps = conn.prepareStatement(sql);
            pps.setString(1,t.getTenNguoiDung());
            pps.setString(2,t.getSoDienThoai());
            pps.setString(3, t.getMatKhauMaHoa());
            pps.setInt(4, t.getLaQuanTriVien());   
            ResultSet rs = pps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            return -1;
        }
	}

	@Override
	public void sua(NguoiDung t) {
		String sql = "UPDATE nguoidung "
				+ "SET tenNguoiDung = ?, soDienThoai = ?, laQuanTriVien = ?"
				+ "WHERE maNguoiDung = " + t.getMaNguoiDung();
		
		try {
			PreparedStatement pps = conn.prepareStatement(sql);
			pps.setString(1,t.getTenNguoiDung());
			pps.setString(2, t.getSoDienThoai());
			pps.setInt(3,t.getLaQuanTriVien());
			pps.execute();
		} catch (SQLException e) {
			
		}
		
	}

	@Override
	public void xoa(NguoiDung t) {
		try {
			Statement stmt = conn.createStatement();
			stmt.execute("Delete from NguoiDung where maNguoiDung = " + t.getMaNguoiDung());
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		
	}
	
	public NguoiDung dangNhap(String soDienThoai, String matKhauDaMaHoa) {
		try {
			String sql = "SELECT * from NguoiDung where soDienThoai = ? and matKhauMaHoa =?; ";
			PreparedStatement pps = conn.prepareStatement(sql);	
			pps.setString(1, soDienThoai);
			pps.setString(2, matKhauDaMaHoa);
			ResultSet rs =  pps.executeQuery();
			rs.next();
			NguoiDung t = new NguoiDung(rs);
			return t;
		
		}
		catch (Exception e) {
			
		}
		return null;
	}

}
