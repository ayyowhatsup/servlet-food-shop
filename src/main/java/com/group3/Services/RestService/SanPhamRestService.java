package com.group3.Services.RestService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.ToNumberPolicy;
import com.google.gson.ToNumberStrategy;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.group3.DAO.NguoiDungDAO;
import com.group3.DAO.SanPhamDAO;
import com.group3.Model.NguoiDung;
import com.group3.Model.SanPham;
import com.group3.Model.TheLoai;
import com.oracle.wls.shaded.org.apache.bcel.generic.NEW;

/**
 * Servlet implementation class SanPhamRestService
 */
public class SanPhamRestService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SanPhamRestService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    /* url: /api/san-pham/* */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(SanPham.class, new SanPhamSerializer())
				.create();
		PrintWriter pr = response.getWriter();
		String str = request.getPathInfo();
		if(str == null || str.length()==1) {
			List<SanPham> arr = new SanPhamDAO().layTatCa();
			String json = gson.toJson(arr);
			pr.println(json);
		}
		else {
			if(str.startsWith("/the-loai")) {
				str = str.replace("/the-loai", "");
				try {
					int maTheLoai = Integer.parseInt(str.substring(1));
					List<SanPham> sps = new SanPhamDAO().layTatCaSanPhamTheoMaTheLoai(maTheLoai);
					if(sps.size()==0) {
						response.setStatus(HttpServletResponse.SC_NOT_FOUND);
					}else {
						pr.println(gson.toJson(sps));
					}
					
				} catch (Exception e) {
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				}
				
			}
			else {
				try {
					int maSanPham = Integer.parseInt(str.substring(1));
					SanPham sp = new SanPhamDAO().layQuaMa(maSanPham);
					if(sp==null) {
						response.setStatus(HttpServletResponse.SC_NOT_FOUND);
					}else {
						pr.println(gson.toJson(sp));
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_CREATED);
	
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(SanPham.class, new SanPhamDeserializer())
				.registerTypeAdapter(SanPham.class, new SanPhamSerializer())
				.create();
	    SanPham sp = gson.fromJson(request.getReader(), SanPham.class);
	    int maSanPham = new SanPhamDAO().taoMoi(sp);
	    sp.setMaSanPham(maSanPham);
		PrintWriter pr = response.getWriter();
		pr.println(gson.toJson(sp));
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 int maSanPham  = Integer.parseInt(req.getPathInfo().substring(1));
		 Gson gson = new GsonBuilder()
				 .addDeserializationExclusionStrategy(new ExclusionStrategy() {
					
					@Override
					public boolean shouldSkipField(FieldAttributes arg0) {
						if(arg0.getName().equals("ma_san_pham")) {
							return true;
						}
						return false;
						
					}
					
					@Override
					public boolean shouldSkipClass(Class<?> arg0) {
						// TODO Auto-generated method stub
						return false;
					}
				})
				 .registerTypeAdapter(SanPham.class, new SanPhamDeserializer())
				 .create();
		 SanPham sp = gson.fromJson(req.getReader(), SanPham.class);
		 sp.setMaSanPham(maSanPham);
		 new SanPhamDAO().sua(sp);
		 resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String str = req.getPathInfo();
		try {
			int maSanPham = Integer.parseInt(str.substring(1));
			SanPham sp2 = new SanPhamDAO().layQuaMa(maSanPham);
			if(sp2==null) {
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}else {
				SanPham sp = new SanPham();
				sp.setMaSanPham(maSanPham);
				new SanPhamDAO().xoa(sp);
				resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}
			
			
		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		
	}

	class SanPhamSerializer implements JsonSerializer<SanPham>{

		@Override
		public JsonElement serialize(SanPham arg0, Type arg1, JsonSerializationContext arg2) {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("ma_san_pham", arg0.getMaSanPham());
			jsonObject.addProperty("ten_san_pham", arg0.getTenSanPham());
			jsonObject.addProperty("gia_tien", arg0.getGiaTien());
			jsonObject.addProperty("mieu_ta", arg0.getMieuTa());
			jsonObject.addProperty("hinh_anh", arg0.getHinhAnh());
			jsonObject.addProperty("don_vi_tinh", arg0.getDonViTinh());
			jsonObject.addProperty("ton_kho", arg0.getTonKho());
			jsonObject.addProperty("ma_the_loai", arg0.getTheLoai().getMaTheLoai());
			return jsonObject;
		}
		
	}
	class SanPhamDeserializer implements JsonDeserializer<SanPham>{

		@Override
		public SanPham deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2)
				throws JsonParseException {
			SanPham sp = new SanPham();
			JsonObject jsonObject = arg0.getAsJsonObject();
			sp.setTenSanPham(jsonObject.get("ten_san_pham").getAsString());
			sp.setGiaTien(jsonObject.get("gia_tien").getAsInt());
			sp.setMieuTa(jsonObject.get("mieu_ta").getAsString());
			sp.setHinhAnh(jsonObject.get("hinh_anh").getAsString());
			sp.setDonViTinh(jsonObject.get("don_vi_tinh").getAsString());
			sp.setTonKho(jsonObject.get("ton_kho").getAsInt());
			TheLoai tl = new TheLoai();
			tl.setMaTheLoai(jsonObject.get("ma_the_loai").getAsInt());
			sp.setTheLoai(tl);
			return sp;
		}
		
	}
}
