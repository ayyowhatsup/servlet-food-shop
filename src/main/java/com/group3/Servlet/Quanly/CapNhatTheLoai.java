package com.group3.Servlet.Quanly;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.group3.DAO.SanPhamDAO;
import com.group3.DAO.TheLoaiDAO;
import com.group3.Model.SanPham;
import com.group3.Model.TheLoai;

/**
 * Servlet implementation class CapNhatTheLoai
 */
public class CapNhatTheLoai extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CapNhatTheLoai() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ma=Integer.parseInt(request.getParameter("id"));
		TheLoaiDAO tld=new TheLoaiDAO();
		TheLoai tl=tld.layQuaMa(ma);
		tl.setTenTheLoai(request.getParameter("tenTheLoai"));
		tld.sua(tl);
		response.sendRedirect("qltheloai");
		
	}

}
