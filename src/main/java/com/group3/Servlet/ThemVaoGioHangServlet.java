package com.group3.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.*;

import com.group3.DAO.SanPhamDAO;
import com.group3.Model.DonHang;
import com.group3.Model.DonHangChiTiet;
import com.group3.Model.SanPham;

/**
 * Servlet implementation class ThemVaoGioHangServlet
 */
public class ThemVaoGioHangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ThemVaoGioHangServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int maSanPham;
		//Mặc định là thêm sản phẩm vào giỏ hàng, số lượng 1
		int soLuong = 1;
		if(request.getParameter("maSanPham")!=null) {
			//Lấy thông tin mã sản phẩm từ param, lấy sản phẩm từ database
			maSanPham = Integer.parseInt(request.getParameter("maSanPham"));
			SanPhamDAO spdao = new SanPhamDAO();
			SanPham thisSanPham = spdao.layQuaMa(maSanPham);
			
			if(thisSanPham!=null) {
				HttpSession session = request.getSession();
				//Nếu giỏ hàng chưa có gì, tạo mới giỏ hàng, thêm sản phẩm
				//được tìm thấy từ database
				if(session.getAttribute("giohang")==null) {
					DonHang donHang = new DonHang();
					List<DonHangChiTiet> arr = new ArrayList();
					DonHangChiTiet donHangChiTiet = new DonHangChiTiet();
					donHangChiTiet.setSoLuong(soLuong);
					donHangChiTiet.setSanPham(thisSanPham);
					arr.add(donHangChiTiet);
					donHang.setDanhSachVatPham(arr);
					donHang.tinhTongTien();
					session.setAttribute("giohang", donHang);
					response.sendRedirect("gio-hang");
				}else {
					//Giỏ hàng đã có sẵn, kiểm tra param số lượng để 
					//giảm số lượng (-1) hoặc xóa sản phẩm (trừ hết), nếu không có vẫn là mặc định thêm 1
					if(request.getParameter("soLuong")!=null) {
						soLuong = Integer.parseInt(request.getParameter("soLuong"));
					}
					DonHang donHang = (DonHang) session.getAttribute("giohang");
					List<DonHangChiTiet> danhSachDonHangChiTiet = donHang.getDanhSachVatPham();
					//Kiểm tra sản phẩm đã có trong giỏ hàng chưa? nếu có thì thay đổi
					//số lượng, chưa thì tạo mới 
					boolean daTonTai = false;
					DonHangChiTiet thisDonHangChiTiet = new DonHangChiTiet();
					for(DonHangChiTiet dhct : danhSachDonHangChiTiet) {
						if(dhct.getSanPham().getMaSanPham()==thisSanPham.getMaSanPham()) {
							daTonTai = true;
							//Sản phẩm đã tồn tại trong giỏ hàng, cập nhật lại thông tin của sản phẩm
							//vào giỏ hàng, vì có thể số lượng tồn kho của sản phẩm đã giảm khi
							//khách hàng khác mua, giỏ hàng lưu trong session thì không tự cập nhật 
							dhct.setSanPham(thisSanPham);
							thisDonHangChiTiet = dhct;
							break;
						}
					}
					if(!daTonTai) {
						DonHangChiTiet donHangChiTiet = new DonHangChiTiet();
						donHangChiTiet.setSoLuong(soLuong);
						donHangChiTiet.setSanPham(thisSanPham);
						danhSachDonHangChiTiet.add(donHangChiTiet);
						
						donHang.setDanhSachVatPham(danhSachDonHangChiTiet);
						donHang.tinhTongTien();
						session.setAttribute("giohang", donHang);
						response.sendRedirect("gio-hang");
					}else {
						int soLuongMoi = thisDonHangChiTiet.getSoLuong() + soLuong;
						//Kiểm tra số lượng của sản phẩm trong đơn đặt hàng
						// giảm số lượng hoặc xóa hẳn sản phẩm
						if(soLuongMoi > thisDonHangChiTiet.getSanPham().getTonKho()) {
							session.setAttribute("giohang", donHang);
							request.setAttribute("mess", "Bạn đã chọn hết số lượng của " + thisDonHangChiTiet.getSanPham().getTenSanPham());
							request.getRequestDispatcher("gio-hang").forward(request, response);
							
						}else {
							thisDonHangChiTiet.setSoLuong(soLuongMoi);
							if(thisDonHangChiTiet.getSoLuong()<=0) {
								danhSachDonHangChiTiet.remove(thisDonHangChiTiet);
							}
							donHang.setDanhSachVatPham(danhSachDonHangChiTiet);
							donHang.tinhTongTien();
							session.setAttribute("giohang", donHang);
							response.sendRedirect("gio-hang");
						}
						
					}
					
				}
			}
		}
	}


}
