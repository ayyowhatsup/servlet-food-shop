package com.group3.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.group3.DAO.DonHangDAO;
import com.group3.DAO.NguoiDungDAO;
import com.group3.Model.DonHang;
import com.group3.Model.DonHangChiTiet;
import com.group3.Model.NguoiDung;

/**
 * Servlet implementation class DonHangServlet
 */
public class DonHangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonHangServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int maDonHang = Integer.parseInt(request.getPathInfo().substring(1));
		DonHang donHang = new DonHangDAO().layQuaMa(maDonHang);
		NguoiDung nd = new NguoiDungDAO().layQuaMa(donHang.getKhachHang().getMaNguoiDung());
		request.setAttribute("dh", donHang);
		request.setAttribute("nd", nd);
		request.getRequestDispatcher("/View/DonHang.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
