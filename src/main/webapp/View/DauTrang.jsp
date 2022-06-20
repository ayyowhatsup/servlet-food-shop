
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/View/css/DauTrang.css">
</head>
<body>
	<div class="logo-wrapper">
		<a href="<%=request.getContextPath()%>/trang-chu" class="logo"><i class="fas fa-utensils"></i>Refood</a>
		<form action="san-pham" method="get">
			<input type="text" name="timkiem" placeholder="Tìm kiếm sản phẩm..." />
			<button type="submit">
				<i class="fa fa-search"></i>
			</button>
		</form>
	</div>

	<nav class="navbar">
	
		<c:if test="${sessionScope.QTV != null && sessionScope.QTV==1}">
		<a href="<%=request.getContextPath()%>/admin/qldonhang">Quản lí cửa hàng</a> 
		</c:if>
		<a href="<%=request.getContextPath()%>/trang-chu">Trang chủ</a> 
		<a href="<%=request.getContextPath()%>/api">Về chúng tôi</a> <a
			href="<%=request.getContextPath()%>/san-pham">Sản phẩm</a>

		<c:if test="${sessionScope.maND == null}">
			<a href="<%=request.getContextPath()%>/dang-nhap">Đăng nhập</a>
		</c:if>
	

		<a id="cart" href="<%=request.getContextPath()%>/gio-hang">
			<i class="fa fa-shopping-basket"></i> Giỏ hàng
		</a>
		
		<c:if test="${sessionScope.maND != null}">
			<a href="<%=request.getContextPath()%>/ca-nhan">Xin chào ${sessionScope.tenND}, </a>
			<a href="<%=request.getContextPath()%>/dang-xuat">Đăng xuất</a>
		</c:if>
		
	</nav>
</body>
</html>


