package com.group3.Servlet;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;

import com.group3.DAO.NguoiDungDAO;
import com.group3.Model.NguoiDung;
import com.group3.TienIch.MaHoaAES;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class DangNhapServlet
 */
@WebServlet("/dang-nhap")
public class DangNhapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DangNhapServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/View/DangNhap.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String soDienThoai = request.getParameter("dang-nhap-sdt");
		String matKhau = request.getParameter("dang-nhap-mat-khau");
		
		//Kiểm tra đầu vào từ người dùng
		if(soDienThoai.equals("") || matKhau.equals("") ) {
			request.setAttribute("mess", "Vui lòng điền đầy đủ các trường!");
			request.getRequestDispatcher("/View/DangNhap.jsp").forward(request, response);
		}
		else {
			//Đăng nhập, kiểm tra tài khoản có tồn tại
			NguoiDung t = new NguoiDungDAO().dangNhap(soDienThoai, MaHoaAES.maHoa(matKhau));
			if(t==null) {
				request.setAttribute("mess", "Đăng nhập không thành công, sai số điện thoại hoặc mật khẩu!");
				request.getRequestDispatcher("/View/DangNhap.jsp").forward(request, response);
			}else {
				//Đăng nhập hợp lệ, lưu thông tin đăng nhập vào session
				HttpSession session = request.getSession();
				session.setAttribute("maND", t.getMaNguoiDung());
				session.setAttribute("tenND", t.getTenNguoiDung());
				session.setAttribute("QTV", t.getLaQuanTriVien());
				response.sendRedirect("trang-chu");
			}
		}
		
	}

}
