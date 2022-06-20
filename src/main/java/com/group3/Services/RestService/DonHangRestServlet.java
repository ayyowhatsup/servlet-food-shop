package com.group3.Services.RestService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.group3.DAO.DonHangDAO;
import com.group3.DAO.SanPhamDAO;
import com.group3.DAO.TheLoaiDAO;
import com.group3.Model.DonHang;
import com.group3.Model.DonHangChiTiet;
import com.group3.Model.NguoiDung;
import com.group3.Model.SanPham;
import com.group3.Model.TheLoai;

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
				.registerTypeAdapter(DonHang.class, new DonHangSerializer())
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
				try {
					int maNguoiDung = Integer.parseInt(str.substring(1));
					List<DonHang> dhs = new DonHangDAO().layDonHangTheoMaNguoiDung(maNguoiDung);
					if(dhs.size()==0) {
						response.setStatus(HttpServletResponse.SC_NOT_FOUND);
					}else {
						pr.println(gson.toJson(dhs));
						response.setStatus(HttpServletResponse.SC_OK);
					}
				} catch (Exception e) {
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				}
	
			}
			else {
				try {
					int maDonHang = Integer.parseInt(str.substring(1));
					DonHang dh = new DonHangDAO().layQuaMa(maDonHang);
					if(dh==null) {
						response.setStatus(HttpServletResponse.SC_NOT_FOUND);
					}else {
						pr.println(gson.toJson(dh));
						response.setStatus(HttpServletResponse.SC_ACCEPTED);
					}
					
					
				} catch (Exception e) {
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				}
				
			}
			
		}
		pr.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new GsonBuilder()
				.setPrettyPrinting()
				.registerTypeAdapter(DonHang.class, new DonHangSerializer())
				.registerTypeAdapter(DonHang.class, new DonHangDeserializer())
				.create();
		
		DonHang dh = gson.fromJson(request.getReader(), DonHang.class);
		dh.setTrangThai("Đặt hàng thành công");
		dh.setThoiGianDatHang(new Date(System.currentTimeMillis()));
		SanPhamDAO spd = new SanPhamDAO();
		boolean check = false;
		for(DonHangChiTiet dhct : dh.getDanhSachVatPham()) {
			SanPham sp = spd.layQuaMa(dhct.getSanPham().getMaSanPham());
			if(sp==null) {
				check = true;
				break;
			}else {
				dhct.setSanPham(sp);
			}
			
		}
		if(check) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}else {
			dh.tinhTongTien();
			int maDonHang = new DonHangDAO().taoMoi(dh);
			dh.setMaDonHang(maDonHang);
			response.setStatus(HttpServletResponse.SC_CREATED);
			response.getWriter().println(gson.toJson(dh));
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getPathInfo();
		try {
			int maDonHang = Integer.parseInt(str.substring(1));
			DonHang dh = new DonHang();
			dh.setMaDonHang(maDonHang);
			DonHangDAO dhd = new DonHangDAO();
			DonHang dh1 = dhd.layQuaMa(maDonHang);
			if(dh1==null) {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
			else {
				response.setContentType("application/json");
				dhd.xoa(dh);
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		
		
	}
	
	
	
	class DonHangSerializer implements JsonSerializer<DonHang>{

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
		
	}
	
	class DonHangDeserializer implements JsonDeserializer<DonHang>{

		@Override
		public DonHang deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2)
				throws JsonParseException {
			DonHang dh = new DonHang();
			JsonObject jsonObject = arg0.getAsJsonObject();
			NguoiDung kh = new NguoiDung();
			kh.setMaNguoiDung(jsonObject.get("ma_nguoi_dung").getAsInt());
			dh.setKhachHang(kh);
			dh.setDiaChiNhanHang(jsonObject.get("dia_chi_nhan_hang").getAsString());
		    JsonArray arr = jsonObject.getAsJsonArray("danh_sach_vat_pham");
		    List<DonHangChiTiet> dchtl = new ArrayList();
		    for(int i=0;i<arr.size();i++) {
		    	JsonObject objj = arr.get(i).getAsJsonObject();
		    	SanPham sp = new SanPham();
		    	DonHangChiTiet dhct= new DonHangChiTiet();
		    	sp.setMaSanPham(objj.get("ma_san_pham").getAsInt());
		    	dhct.setSanPham(sp);
		    	dhct.setSoLuong(objj.get("so_luong").getAsInt());
		    	dchtl.add(dhct);
		    }
			dh.setDanhSachVatPham(dchtl);
			return dh;
		}
		
	}
	
}
