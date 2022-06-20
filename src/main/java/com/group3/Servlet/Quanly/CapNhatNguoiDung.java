package com.group3.Servlet.Quanly;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.group3.DAO.NguoiDungDAO;
import com.group3.DAO.SanPhamDAO;
import com.group3.DAO.TheLoaiDAO;
import com.group3.Model.NguoiDung;
import com.group3.Model.SanPham;
import com.group3.Model.TheLoai;

/**
 * Servlet implementation class CapNhatNguoiDung
 */
public class CapNhatNguoiDung extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CapNhatNguoiDung() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NguoiDungDAO a=new NguoiDungDAO();
		String ma=request.getParameter("id");
		NguoiDung nd =a.layQuaMa(Integer.parseInt(ma));
		request.setAttribute("nguoidung", nd);
		request.getRequestDispatcher("/View/CapNhatNguoiDung.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ten =request.getParameter("tenNguoiDung");
		String sodienthoai =request.getParameter("soDienThoai");
		int chucnang =Integer.parseInt(request.getParameter("laQuanTriVien"));
		NguoiDung nd=new NguoiDung();
		nd.setMaNguoiDung(Integer.parseInt(request.getParameter("maNguoiDung")));
		nd.setTenNguoiDung(ten);
		nd.setSoDienThoai(sodienthoai);
		nd.setLaQuanTriVien(chucnang);
		NguoiDungDAO ndd =new NguoiDungDAO();
		ndd.sua(nd);
		response.sendRedirect("qlnguoidung");
	
		
	}

}
