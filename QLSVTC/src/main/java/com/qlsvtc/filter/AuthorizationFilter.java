package com.qlsvtc.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.qlsvtc.model.NhanVienLoginModel;

import java.io.IOException;
@Component
public class AuthorizationFilter implements Filter {

	private ServletContext context = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.setContext(filterConfig.getServletContext());
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();

		String url = request.getRequestURI();
		NhanVienLoginModel model = (NhanVienLoginModel) session.getAttribute("USERMODEL");
		// neu la dang nhap thi cho qua
		if (url.indexOf("/dang-nhap") != -1 || url.equals("/") || url.indexOf("/api") != -1 ) {
			filterChain.doFilter(servletRequest, servletResponse);
		}

		// neu ko phai dang nhap, co ten nhom thi chi nhom do dc qua, ko co ten nhom thi
		// nhom nao cung co the qua nhung phai dang nhap
		else if (model != null) {
			if (url.indexOf("/pgv") != -1) {

				switch (model.getTenNhom()) {
				case "PGV":
					filterChain.doFilter(servletRequest, servletResponse);
					break;
				case "KHOA":
					response.sendRedirect(request.getContextPath()
							+ "/dang-nhap?action=login&message=not_permission&alert=danger");
					break;
				case "SV":
					response.sendRedirect(request.getContextPath()
							+ "/dang-nhap?action=login&message=not_permission&alert=danger");
					break;

				}

			}

			else if (url.indexOf("/chinhanh") != -1) {

				switch (model.getTenNhom()) {
				case "PGV":
					response.sendRedirect(request.getContextPath()
							+ "/dang-nhap?action=login&message=not_permission&alert=danger");
					break;
				case "KHOA":
					filterChain.doFilter(servletRequest, servletResponse);
					break;
				case "SV":
					response.sendRedirect(request.getContextPath()
							+ "/dang-nhap?action=login&message=not_permission&alert=danger");
					break;

				}

			}

			else if (url.indexOf("/sv") != -1) {

				switch (model.getTenNhom()) {
				case "PGV":
					response.sendRedirect(request.getContextPath()
							+ "/dang-nhap?action=login&message=not_permission&alert=danger");
					break;
				case "KHOA":
					response.sendRedirect(request.getContextPath()
							+ "/dang-nhap?action=login&message=not_permission&alert=danger");
					break;
				case "SV":
					filterChain.doFilter(servletRequest, servletResponse);
					break;

				}

			}

			//khi khong co ten nhom trong url
			else filterChain.doFilter(servletRequest, servletResponse);
			
		}
		//khi khong dang nhap
		else response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=not_login&alert=danger");
	}

	@Override
	public void destroy() {

	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}
}
