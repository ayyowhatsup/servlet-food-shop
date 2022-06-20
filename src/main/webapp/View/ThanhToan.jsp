<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Refood - Thanh toán</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/View/css/ThanhToan.css">
</head>
<body>
	<div class="thanh-toan">
		<div class="thanh-toan-thong-tin">
			<a href="trang-chu">
				<h1>Refood</h1>
			</a>
			<h3>Thông tin giao hàng</h3>
			<form action="thanh-toan" method="post">
				<div class="input">
					<span class='tieu-de'>Họ và tên</span></br>
					<div>${nguoiDung.tenNguoiDung}</div>
				</div>
				<div class="input">
					<span class='tieu-de'>Số điện thoại</span></br>
					<div>${nguoiDung.soDienThoai}</div>
				</div>
				<div class="input">
					<span class='tieu-de'>Địa chỉ</span></br> <input type="text"
						name="diaChi" placeholder="Địa chỉ">
				</div>
				<div class="hanh-dong">
					<a href="gio-hang">Giỏ hàng</a> <input class="button-thanh-toan"
						value="Đặt hàng" type="submit">
					</button>
					</input>
				</div>
				<h3>${mess}</h3>
			</form>
		</div>
		<div class="thanh-toan-san-pham">
			<div class="danh-sach-san-pham">
				<c:forEach items="${donHang.danhSachVatPham}" var="sp">
					<div class="san-pham">
						<div class="san-pham-anh">
							<img src="${sp.sanPham.hinhAnh}" alt="${sp.sanPham.tenSanPham}">
						</div>
						<div>${sp.sanPham.tenSanPham}</div>
						<span>X</span>
						<div>${sp.soLuong}</div>
						<div>
							<fmt:setLocale value="vi_VN" />
							<fmt:formatNumber value="${sp.sanPham.giaTien * sp.soLuong}"
								type="currency" />
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="tong-tien">
				<div class="tien-thanh-phan">
					<h4>Tạm tính</h4>
					<h3>
						<fmt:setLocale value="vi_VN" />
						<fmt:formatNumber value="${donHang.thanhTien}" type="currency" />
					</h3>
				</div>
				<div class="tien-thanh-phan">
					<h4>Phí vận chuyển</h4>
					<h3>
						<fmt:setLocale value="vi_VN" />
						<fmt:formatNumber value="0" type="currency" />
					</h3>
				</div>

			</div>
			<div class="tien-thanh-phan">
				<h3>THÀNH TIỀN</h3>
				<h3>
					<fmt:setLocale value="vi_VN" />
					<fmt:formatNumber value="${donHang.thanhTien}" type="currency" />
				</h3>
			</div>
		</div>
	</div>
</body>
</html>