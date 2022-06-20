package com.group3.filter;

import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

/**
 * Kiểm tra người dùng đã đăng nhập hay chưa, muốn
 * truy cập /thanh-toan hay là xem thông tin cá nhân /ca-nhan 
 * thì cần đăng nhập trước
 *
 */


public class KiemTraDangNhapFilter extends HttpFilter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public KiemTraDangNhapFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		if(session.getAttribute("maND")==null||session.getAttribute("tenND")==null) {
			req.setAttribute("mess", "Vui lòng đăng nhập để tiếp tục!");
			req.getRequestDispatcher("/View/DangNhap.jsp").forward(req, resp);
		}else {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
