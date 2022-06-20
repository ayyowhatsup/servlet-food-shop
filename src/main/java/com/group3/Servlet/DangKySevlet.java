package com.group3.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.group3.DAO.NguoiDungDAO;
import com.group3.Model.NguoiDung;
import com.group3.TienIch.MaHoaAES;

/**
 * Servlet implementation class DangKySevlet
 */
public class DangKySevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DangKySevlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/View/DangKy.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String tenNguoiDung = request.getParameter("dang-ky-ho-ten");
		String soDienThoai = request.getParameter("dang-ky-sdt");
		String matKhau = request.getParameter("dang-ky-matkhau");
		String nhapLaiMatKhau = request.getParameter("dang-ky-matkhau-nhaplai");
		
		//Kiểm tra đầu vào
		if(tenNguoiDung.equals("")||soDienThoai.equals("")||matKhau.equals("")||nhapLaiMatKhau.equals("")) {
			request.setAttribute("mess", "Vui lòng nhập đầy đủ các trường!");
			request.getRequestDispatcher("/View/DangKy.jsp").forward(request, response);
		}else {
			if(!matKhau.equals(nhapLaiMatKhau)) {
				request.setAttribute("mess", "Mật khẩu và Nhập lại mật khẩu không giống nhau!");
				request.getRequestDispatcher("/View/DangKy.jsp").forward(request, response);
			}else {
				//Kiểm tra số điện thoại đăng kí đã tồn tại trong hệ thống hay chưa
				List<NguoiDung> arr = new NguoiDungDAO().layTatCa();
				NguoiDung nd = new NguoiDung(tenNguoiDung,soDienThoai,MaHoaAES.maHoa(matKhau));
				boolean check = false;
				for(NguoiDung nd1 : arr) {
					if(nd1.getSoDienThoai().equals(nd.getSoDienThoai())) {
						check = true;
						break;
					}
				}
				if(check) {
					request.setAttribute("mess", "Số điện thoại đăng ký đã tồn tại!");
					request.getRequestDispatcher("/View/DangKy.jsp").forward(request, response);
				}else {
					//Đăng kí thành công, tạo mới người dùng
					new NguoiDungDAO().taoMoi(nd);
					response.sendRedirect("dang-nhap");
				}
			}
			
		}
		
		
	}

}
