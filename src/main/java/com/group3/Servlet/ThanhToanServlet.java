package com.group3.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import com.group3.DAO.DonHangDAO;
import com.group3.DAO.NguoiDungDAO;
import com.group3.DAO.SanPhamDAO;
import com.group3.Model.DonHang;
import com.group3.Model.DonHangChiTiet;
import com.group3.Model.NguoiDung;
import com.group3.Model.SanPham;
import com.group3.TienIch.KetNoiCSDL;

/**
 * Servlet implementation class ThanhToan
 */
public class ThanhToanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThanhToanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int maND = (Integer) session.getAttribute("maND");
		NguoiDung nd = new NguoiDungDAO().layQuaMa(maND);
		
		//Lấy thông tin giỏ hàng từ trong session, hiển thị ra view
		DonHang dh = (DonHang) session.getAttribute("giohang");
		request.setAttribute("donHang", dh);
		request.setAttribute("nguoiDung", nd);
		request.getRequestDispatcher("/View/ThanhToan.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = KetNoiCSDL.ketNoiPostgreSQL();
		SanPhamDAO spdao = new SanPhamDAO(conn);
		String diaChi = request.getParameter("diaChi");
		HttpSession session = request.getSession();
		
		//Lấy thông tin khách hàng và giỏ hàng
		int maND = (Integer) session.getAttribute("maND");
		NguoiDung nd = new NguoiDungDAO(conn).layQuaMa(maND);
		DonHang dh = (DonHang) session.getAttribute("giohang");
		
		//Kiểm tra địa chỉ có hợp lệ trả lại giao hiện nếu cần thiết
		if(diaChi.equals("")) {
			request.setAttribute("mess", "Vui lòng điền địa chỉ nhận hàng!");
			request.setAttribute("donHang", dh);
			request.setAttribute("nguoiDung", nd);
			request.getRequestDispatcher("/View/ThanhToan.jsp").forward(request, response);
		}
		else {
			//Kiểm tra lại số lượng Tồn kho của tất cả sản phẩm có trong giỏ hàng,
			//Thông tin về Tồn kho của sản Phẩm lưu trong session không được cập nhật lại
			//>Cần phải chọc vào DB kiểm tra
			boolean kiemTraTonKho = false;
			List<DonHangChiTiet> danhSachDonHangChiTiet = dh.getDanhSachVatPham();
			for(DonHangChiTiet dhct : danhSachDonHangChiTiet ) {
				if(dhct.getSoLuong() > spdao.layQuaMa(dhct.getSanPham().getMaSanPham()).getTonKho()) {
					kiemTraTonKho = true;
					break;
				}
			}
			if(kiemTraTonKho) {
				request.setAttribute("mess", "Một số sản phẩm đã hết hoặc số lượng còn lại không đủ, Vui lòng kiểm tra lại số lượng");
				request.getRequestDispatcher("gio-hang").forward(request, response);
			}
			else {
				
				//Tạo đơn hàng mới
				dh.setDiaChiNhanHang(diaChi);
				dh.setThoiGianDatHang(new Date(System.currentTimeMillis()));
				dh.setTrangThai("Đặt hàng thành công");
				dh.setKhachHang(nd);
				new DonHangDAO().taoMoi(dh);
				for(DonHangChiTiet dhct : danhSachDonHangChiTiet ) {
					SanPham thisSanPham = spdao.layQuaMa(dhct.getSanPham().getMaSanPham());
					thisSanPham.setTonKho(thisSanPham.getTonKho()-dhct.getSoLuong());
					spdao.sua(thisSanPham);
				}		
				session.removeAttribute("giohang");
				response.sendRedirect("ca-nhan");
			}
			
		}
	}
	
}
