package com.group3.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import com.group3.DAO.DonHangDAO;
import com.group3.DAO.NguoiDungDAO;
import com.group3.Model.DonHang;
import com.group3.Model.NguoiDung;
import com.group3.TienIch.KetNoiCSDL;

/**
 * Servlet implementation class CaNhanServlet
 */
public class CaNhanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CaNhanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = KetNoiCSDL.ketNoiPostgreSQL();
		HttpSession session = request.getSession();
		int maND = (Integer) session.getAttribute("maND");
		NguoiDung nd = new NguoiDungDAO(conn).layQuaMa(maND);
		request.setAttribute("nd", nd);
		List<DonHang> arr = new DonHangDAO(conn).layThongTinDonHangTheoMaNguoiDung(maND);
		request.setAttribute("dsdh", arr);
		request.getRequestDispatcher("/View/CaNhan.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
