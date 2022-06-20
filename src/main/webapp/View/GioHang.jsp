<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Refood - Giỏ hàng</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/View/css/GioHang.css">
</head>
<body>
	<header>
		<jsp:include page="DauTrang.jsp"></jsp:include>
	</header>

	<div class="gio-hang-wrap">
		<div class="gio-hang">
			<h1>Giỏ hàng của bạn</h1>
			<div class="gio-hang-inner">
				<div class="danh-sach-vat-pham">
				
				<c:if test="${gioHang==null || gioHang.danhSachVatPham.size() == 0}">
					<div class="khong-co-gi">
					<h4>Giỏ hàng hiện tại đang trống</h4>
					<a class="nut-bam" href="san-pham">Tiếp tục mua sắm ></a>
					</div>
				</c:if>
					<c:forEach items="${gioHang.danhSachVatPham}" var="sp">
						<div class="vat-pham">
							<div class=vat-pham-hinh-anh>
								<img src="${sp.sanPham.hinhAnh}" alt="${sp.sanPham.tenSanPham}" />
							</div>
							<div class="vat-pham-chi-tiet">
								<h2>${sp.sanPham.tenSanPham}</h2>
								<div>
								Giá: 
									<fmt:setLocale value="vi_VN" />
									<fmt:formatNumber value="${sp.sanPham.giaTien}"
										type="currency" />
										/1 ${sp.sanPham.donViTinh}
								, Còn lại ${sp.sanPham.tonKho} 
								</div>

							</div>
							<div class="vat-pham-so-luong">
								<div class="btn">
									<a href="them-vao-gio-hang?maSanPham=${sp.sanPham.maSanPham}&soLuong=-1">-</a>
								</div>
								<h3>${sp.soLuong}</h3>
								<div class='btn'>
									<a href="them-vao-gio-hang?maSanPham=${sp.sanPham.maSanPham}">+</a>
								</div>
							</div>

							<h3 class="vat-pham-thanh-tien">
								<fmt:setLocale value="vi_VN" />
								<fmt:formatNumber value="${sp.soLuong*sp.sanPham.giaTien}"
									type="currency" />
							</h3>
							<a href="them-vao-gio-hang?maSanPham=${sp.sanPham.maSanPham}&soLuong=-${sp.soLuong}" class="vat-pham-xoa"> Xóa </a>
						</div>
					</c:forEach>

				</div>
				<c:if test="${gioHang!=null && gioHang.danhSachVatPham.size() > 0}">
					<div class="thong-tin-thanh-toan">
						<div class="tong-tien">
							<div>Tổng tiền</div>
							<h3>
							<fmt:setLocale value="vi_VN" />
									<fmt:formatNumber value="${gioHang.thanhTien}"
										type="currency" /></h3>
						</div>
						<p class="dieu-khoan">Khi bấm nút "Thanh toán" đồng nghĩa Khách hàng đã hiểu và
							đồng ý các Điều khoản dịch vụ của Refood.</p>
						<a class="nut-bam" href="thanh-toan">Thanh toán</a>
						<a class="nut-bam" href="san-pham">Tiếp tục mua sắm ></a>

					</div>
				</c:if>

			</div>
			<h2 class="thong-bao">${mess}</h2>
		</div>
	</div>
</body>
</html>