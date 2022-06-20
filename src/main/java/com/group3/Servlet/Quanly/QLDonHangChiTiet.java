package com.group3.Servlet.Quanly;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.group3.DAO.DonHangChiTietDAO;
import com.group3.DAO.DonHangDAO;
import com.group3.DAO.SanPhamDAO;
import com.group3.DAO.TheLoaiDAO;
import com.group3.Model.DonHang;
import com.group3.Model.DonHangChiTiet;
import com.group3.Model.SanPham;
import com.group3.Model.TheLoai;

/**
 * Servlet implementation class QLDonHangChiTiet
 */
public class QLDonHangChiTiet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QLDonHangChiTiet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DonHangChiTietDAO dhctd=new DonHangChiTietDAO();
		int ma=Integer.parseInt(request.getParameter("id"));
		DonHangDAO dhd=new DonHangDAO();
		DonHang dh=dhd.layQuaMa(ma);
		List<DonHangChiTiet> list=dhctd.layDanhSachVatPhamHoaDonTheoMa(ma);
		request.setAttribute("khachhang",dh.getKhachHang().getTenNguoiDung());	
		request.setAttribute("data", dh);
		request.getRequestDispatcher("/View/DanhSachDatHang.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
