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
 * Servlet implementation class ThemSanPham
 */
public class ThemSanPham extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThemSanPham() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TheLoaiDAO a=new TheLoaiDAO();
		List<TheLoai> l =a.layTatCa();
		request.setAttribute("dstheloai", l);
		request.getRequestDispatcher("/View/ThemSanPham.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated mprivate int maSanPham;
		
		
		String ten =request.getParameter("tenSanPham");
		int gia =Integer.parseInt(request.getParameter("giaTien"));
		String mieuta =request.getParameter("mieuTa");
		String hinhAnh =request.getParameter("hinhAnh");
		String donViTinh =request.getParameter("donViTinh");
		int tonkho =Integer.parseInt(request.getParameter("tonKho"));
		TheLoai theloai =new TheLoai();
		theloai.setMaTheLoai(Integer.parseInt(request.getParameter("theloai")));
		
		SanPham sp=new SanPham();
		sp.setTenSanPham(ten);
		sp.setDonViTinh(donViTinh);
		sp.setGiaTien(gia);
		sp.setHinhAnh(hinhAnh);
		sp.setMieuTa(mieuta);
		sp.setTheLoai(theloai);
		sp.setTonKho(tonkho);
		
		SanPhamDAO spd =new SanPhamDAO();
		spd.taoMoi(sp);
		response.sendRedirect("qlsanpham");
	
		
		
		
	}

}
