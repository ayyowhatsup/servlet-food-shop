package com.group3.Services.RestService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.group3.DAO.NguoiDungDAO;
import com.group3.Model.NguoiDung;
import com.group3.TienIch.MaHoaAES;

/**
 * Servlet implementation class DangNhapRestServlet
 */
public class DangNhapRestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DangNhapRestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str  = request.getPathInfo();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonObject jsonObject =  gson.fromJson(request.getReader(), JsonObject.class);
		if(jsonObject.get("so_dien_thoai")==null || jsonObject.get("mat_khau")==null ) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}else {
			String matKhau = jsonObject.get("mat_khau").getAsString();
			String soDienThoai = jsonObject.get("so_dien_thoai").getAsString();
			NguoiDungDAO ndd = new NguoiDungDAO();
			NguoiDung nd = ndd.dangNhap(soDienThoai, MaHoaAES.maHoa(matKhau)); 
			if(nd==null) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}else {
				response.setStatus(HttpServletResponse.SC_OK);
				JsonObject obj = new JsonObject();
				obj.addProperty("trang_thai", "Đăng nhập thành công");
				JsonObject obj2 = new JsonObject();
				obj2.addProperty("ma_nguoi_dung", nd.getMaNguoiDung());
				obj2.addProperty("so_dien_thoai", nd.getSoDienThoai());
				obj2.addProperty("la_quan_tri_vien", nd.getLaQuanTriVien());
				obj2.addProperty("ten_nguoi_dung", nd.getTenNguoiDung());
				obj.add("nguoi_dung", obj2);
				response.getWriter().println(gson.toJson(obj));
			}
		}
		
	}

}
