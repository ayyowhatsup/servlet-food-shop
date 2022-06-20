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
import com.group3.Model.NguoiDung;
import com.group3.Model.SanPham;

/**
 * Servlet implementation class QLNguoiDung
 */
public class QLNguoiDung extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QLNguoiDung() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		NguoiDungDAO ndd =new NguoiDungDAO();
		List<NguoiDung> list= ndd.layTatCa();
		
		request.setAttribute("data", list);
		request.getRequestDispatcher("/View/QLNguoiDung.jsp").forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
