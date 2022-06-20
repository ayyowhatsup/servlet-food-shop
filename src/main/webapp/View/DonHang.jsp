<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Refood - Chi tiết đơn hàng</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/View/css/DonHang.css">
</head>
<body>
	<header>
		<jsp:include page="DauTrang.jsp"></jsp:include>
	</header>

	<div class="trang-ca-nhan">
		<div class="trang-ca-nhan-ben-trong">
			<h1>TÀI KHOẢN CỦA BẠN</h1>
			<div class="noi-dung">
				<div class="nav-bar">
					<h2 class="nav-bar-tieu-de">TÀI KHOẢN</h2>
					<ul>
						<li><a href="<%=request.getContextPath()%>/ca-nhan">Thông
								tin tài khoản</a></li>
						<li><a href="<%=request.getContextPath()%>/dang-xuat">Đăng
								xuất</a></li>
					</ul>
				</div>

				<div class="noi-dung-chinh">
					<h2 class="noi-dung-chinh-tieu-de">CHI TIẾT ĐƠN HÀNG</h2>

					<div class="thong-tin-don-hang">
						<div>Đơn hàng #${dh.maDonHang}</div>
						<div>
							Thời gian đặt hàng:
							<fmt:formatDate value="${dh.thoiGianDatHang}"
								pattern="dd-MM-yyyy HH:mm:ss" />
						</div>
						<a href="<%=request.getContextPath()%>/ca-nhan">Quay lại trang tài khoản</a>
					</div>


					<div>
						<h3>DANH SÁCH SẢN PHẨM</h3>

						<div class="danh-sach-san-pham">
							<div class="san-pham title">
								<div class="ten-san-pham">Sản phẩm</div>
								<div class="don-gia">Đơn giá</div>
								<div class="so-luong">Số lượng</div>
								<div class="thanh-tien">Thành tiền</div>
							</div>

							<c:forEach items="${dh.danhSachVatPham}" var="dhct">
								<div class="san-pham">
									<div class="ten-san-pham">${dhct.sanPham.tenSanPham}</div>
									<div class="don-gia">
										<fmt:setLocale value="vi_VN" />
										<fmt:formatNumber value="${dhct.sanPham.giaTien}"
											type="currency" />
									</div>
									<div class="so-luong">${dhct.soLuong}</div>
									<div class="thanh-tien">
										<fmt:setLocale value="vi_VN" />
										<fmt:formatNumber
											value="${dhct.sanPham.giaTien * dhct.soLuong}"
											type="currency" />
									</div>
								</div>
							</c:forEach>
						</div>
						<div class='tien'>
							<div class="tong-tien">
								<div class="tien-thanh-phan">
									<div>Tạm tính</div>
									<div>
										<fmt:setLocale value="vi_VN" />
										<fmt:formatNumber value="${dh.thanhTien}" type="currency" />
									</div>
								</div>
								<div class="tien-thanh-phan">
									<div>Phí vận chuyển</div>
									<div>
										<fmt:setLocale value="vi_VN" />
										<fmt:formatNumber value="0" type="currency" />
									</div>
								</div>

								<div class="tien-thanh-phan">
									<div>Thành tiền</div>
									<div>
										<fmt:setLocale value="vi_VN" />
										<fmt:formatNumber value="${dh.thanhTien}" type="currency" />
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="dia-chi">
						<div>
							<h3 class="thong-tin-title">ĐỊA CHỈ NHẬN THANH TOÁN</h3>
							<div>${nd.tenNguoiDung}</div>
							<div>${dh.diaChiNhanHang}</div>
							<div>${nd.soDienThoai}</div>
						</div>
						<div>
							<h3 class="thong-tin-title">TRẠNG THÁI ĐƠN HÀNG</h3>
							<div>${dh.trangThai}</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>