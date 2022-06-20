package com.group3.Servlet.Quanly;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.group3.DAO.DonHangDAO;
import com.group3.Model.DonHang;

/**
 * Servlet implementation class CapNhatDonHang
 */
public class CapNhatDonHang extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CapNhatDonHang() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ma =Integer.parseInt(request.getParameter("id"));
		DonHangDAO dhd=new DonHangDAO();
		DonHang dh =dhd.layQuaMa(ma);
		dh.setTrangThai("Đơn hàng đã hoàn thành");
		dhd.suaTrangThai(dh);
		response.sendRedirect("qldonhang");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
