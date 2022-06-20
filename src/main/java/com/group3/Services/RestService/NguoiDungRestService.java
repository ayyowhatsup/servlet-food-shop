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
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.group3.DAO.DonHangDAO;
import com.group3.DAO.NguoiDungDAO;
import com.group3.Model.DonHang;
import com.group3.Model.NguoiDung;
import com.group3.TienIch.MaHoaAES;

/**
 * Servlet implementation class NguoiDungRestService
 */
public class NguoiDungRestService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NguoiDungRestService() {
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
				.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
				.setPrettyPrinting()
				.create();
		PrintWriter pr = response.getWriter();
		String str = request.getPathInfo();
		if(str == null || str.length()==1) {
			List<NguoiDung> arr = new NguoiDungDAO().layTatCa();
			String json = gson.toJson(arr);
			pr.println(json);
		}
		else {
			try {
				int maNguoiDung = Integer.parseInt(str.substring(1));
				
				NguoiDung nd = new NguoiDungDAO().layQuaMa(maNguoiDung);
				if(nd==null) {
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				}else {
					pr.println(gson.toJson(nd));
				}
				
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
			
		}
		pr.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new GsonBuilder()
				.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
				.registerTypeAdapter(NguoiDung.class, new NguoiDungDeserializer())
				.setPrettyPrinting()
				.create();
		NguoiDung nd = gson.fromJson(request.getReader(),NguoiDung.class);
		int maNguoiDung = new NguoiDungDAO().taoMoi(nd);
		nd.setMaNguoiDung(maNguoiDung);
		response.setStatus(HttpServletResponse.SC_CREATED);
		response.getWriter().println(gson.toJson(nd));
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String str = req.getPathInfo();
		try {
			int maNguoiDung = Integer.parseInt(str.substring(1));
			NguoiDung nd = new NguoiDung();
			nd.setMaNguoiDung(maNguoiDung);
			NguoiDung nd1 = new NguoiDungDAO().layQuaMa(maNguoiDung);
			if(nd1==null) {
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}else {
				resp.setContentType("application/json");
				new NguoiDungDAO().xoa(nd);
				resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}
		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String str = req.getPathInfo();
		try {
			int maNguoiDung  = Integer.parseInt(str.substring(1));
			Gson gson = new GsonBuilder()
					.registerTypeAdapter(NguoiDung.class, new NguoiDungDeserializer())
					.setPrettyPrinting()
					.create();
			
			NguoiDung nd = gson.fromJson(req.getReader(), NguoiDung.class);
			nd.setMaNguoiDung(maNguoiDung);
			NguoiDungDAO ndd = new NguoiDungDAO();
			ndd.sua(nd);
			resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		
	}
	class NguoiDungDeserializer implements JsonDeserializer<NguoiDung>{

		@Override
		public NguoiDung deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2)
				throws JsonParseException {
			JsonObject obj = arg0.getAsJsonObject();
			NguoiDung nd = new NguoiDung();
			nd.setTenNguoiDung(obj.get("ten_nguoi_dung").getAsString());
			nd.setMatKhauMaHoa(MaHoaAES.maHoa(obj.get("mat_khau").getAsString()));
			nd.setLaQuanTriVien(obj.get("la_quan_tri_vien").getAsInt());
			nd.setSoDienThoai(obj.get("so_dien_thoai").getAsString());
			return nd;
		}
		
	}
}
