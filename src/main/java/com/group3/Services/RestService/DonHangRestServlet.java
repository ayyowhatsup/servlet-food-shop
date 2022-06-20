package com.group3.Services.RestService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.group3.DAO.DonHangDAO;
import com.group3.DAO.SanPhamDAO;
import com.group3.Model.DonHang;
import com.group3.Model.DonHangChiTiet;
import com.group3.Model.SanPham;

/**
 * Servlet implementation class DonHangRestServlet
 */
public class DonHangRestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonHangRestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(DonHang.class, new JsonSerializer<DonHang>() {

					@Override
					public JsonElement serialize(DonHang arg0, Type arg1, JsonSerializationContext arg2) {
						JsonObject jsonObject = new JsonObject();
						jsonObject.addProperty("ma_don_hang", arg0.getMaDonHang());
						jsonObject.addProperty("thoi_gian_dat_hang", arg0.getThoiGianDatHang().toString());
						jsonObject.addProperty("thanh_tien", arg0.getThanhTien());
						jsonObject.addProperty("trang_thai", arg0.getTrangThai());
						
						JsonArray danhSachVP = new JsonArray();
						for(DonHangChiTiet dhct : arg0.getDanhSachVatPham()) {
							JsonObject vatPham = new JsonObject();
							vatPham.addProperty("ma_san_pham", dhct.getSanPham().getMaSanPham());
							vatPham.addProperty("so_luong", dhct.getSoLuong());
							danhSachVP.add(vatPham);
						}
						jsonObject.addProperty("ma_nguoi_dung", arg0.getKhachHang().getMaNguoiDung());
						jsonObject.add("danh_sach_vat_pham", danhSachVP);
						jsonObject.addProperty("dia_chi_nhan_hang", arg0.getDiaChiNhanHang());
						return jsonObject;
					}
				})
				.setPrettyPrinting()
				.create();
		
		
		PrintWriter pr = response.getWriter();
		String str = request.getPathInfo();
		if(str == null || str.length()==1) {
			List<DonHang> arr = new DonHangDAO().layTatCa();
			String json = gson.toJson(arr);
			pr.println(json);
		}
		else {
			if(str.startsWith("/nguoi-dung")) {
				str = str.replace("/nguoi-dung", "");
				int maNguoiDung = Integer.parseInt(str.substring(1));
				List<DonHang> dhs = new DonHangDAO().layThongTinDonHangTheoMaNguoiDung(maNguoiDung);
				pr.println(gson.toJson(dhs));
			}
			else {
				int maDonHang = Integer.parseInt(str.substring(1));
				DonHang dh = new DonHangDAO().layQuaMa(maDonHang);
				pr.println(gson.toJson(dh));
			}
			
		}
		pr.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
