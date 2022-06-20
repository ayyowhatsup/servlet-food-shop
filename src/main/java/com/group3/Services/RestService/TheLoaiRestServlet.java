package com.group3.Services.RestService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.group3.DAO.TheLoaiDAO;
import com.group3.Model.TheLoai;

/**
 * Servlet implementation class TheLoaiRestServlet
 */
public class TheLoaiRestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TheLoaiRestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
				.setPrettyPrinting().create();
		PrintWriter pr = response.getWriter();
		String str = request.getPathInfo();
		if (str == null || str.length() == 1) {
			List<TheLoai> danhSachTheLoai = new TheLoaiDAO().layTatCa();
			String json = gson.toJson(danhSachTheLoai);
			pr.println(json);
		} else {
			int maTheLoai = Integer.parseInt(str.substring(1));
			TheLoai tl = new TheLoaiDAO().layQuaMa(maTheLoai);
			String json = gson.toJson(tl);
			pr.println(json);
		}

		pr.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
				.addDeserializationExclusionStrategy(new ExclusionStrategy() {

					@Override
					public boolean shouldSkipField(FieldAttributes arg0) {
						if (arg0.getName().toLowerCase().startsWith("ma")) {
							return true;
						}
						return false;
					}

					@Override
					public boolean shouldSkipClass(Class<?> arg0) {
						// TODO Auto-generated method stub
						return false;
					}
				}).create();

		TheLoai tl = gson.fromJson(request.getReader(), TheLoai.class);
		int maTheLoai = new TheLoaiDAO().taoMoi(tl);
		response.setStatus(HttpServletResponse.SC_CREATED);
		response.getWriter()
				.println(new GsonBuilder().setPrettyPrinting()
						.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
						.toJson(new TheLoaiDAO().layQuaMa(maTheLoai)));

	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String str = req.getPathInfo();
		int maTheLoai = Integer.parseInt(str.substring(1));
		TheLoai tl = new TheLoai();
		tl.setMaTheLoai(maTheLoai);
		resp.setContentType("application/json");
		new TheLoaiDAO().xoa(tl);
		resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String str = req.getPathInfo();
		int maTheLoai = Integer.parseInt(str.substring(1));

		resp.setContentType("application/json");
		Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
				.addDeserializationExclusionStrategy(new ExclusionStrategy() {

					@Override
					public boolean shouldSkipField(FieldAttributes arg0) {
						if (arg0.getName().toLowerCase().startsWith("ma")) {
							return true;
						}
						return false;
					}

					@Override
					public boolean shouldSkipClass(Class<?> arg0) {
						// TODO Auto-generated method stub
						return false;
					}
				}).create();
		
		TheLoai tl = gson.fromJson(req.getReader(), TheLoai.class);
		tl.setMaTheLoai(maTheLoai);
		new TheLoaiDAO().sua(tl);
		resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
}
