<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Refood - Cá nhân</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/View/css/CaNhan.css">
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
						<li><a href="ca-nhan">Thông tin tài khoản</a></li>
						<li><a href="dang-xuat">Đăng xuất</a></li>
					</ul>
				</div>

				<div class="noi-dung-chinh">
					<h2 class="noi-dung-chinh-tieu-de">THÔNG TIN TÀI KHOẢN</h2>
					<div class="thong-tin-ca-nhan">
						<div>${nd.tenNguoiDung}</div>
						<div>${nd.soDienThoai}</div>
					</div>
					<div>
						<h3 >DANH SÁCH ĐƠN HÀNG MỚI NHẤT</h3>
						<div class="danh-sach-don-hang">
							<a class="don-hang title">
								<div class="ma-don-hang">Mã đơn hàng</div>
								<div class="tg-don-hang">Ngày đặt</div>
								<div class="tien-don-hang">Thành tiền</div>
								<div class="trangthai-don-hang">Trạng thái</div>
							</a>

							<c:if test="${dsdh.size() ==0}">Bạn chưa thực hiện đơn hàng nào!</c:if>

							<c:forEach items="${dsdh}" var="donhang">
								<a href="ca-nhan/don-hang/${donhang.maDonHang}" class="don-hang">
									<div class="ma-don-hang">#${donhang.maDonHang}</div>
									<div class="tg-don-hang">
										<fmt:formatDate value="${donhang.thoiGianDatHang}"
											pattern="dd-MM-yyyy" />
									</div>
									<div class="tien-don-hang">
										<fmt:setLocale value="vi_VN" />
										<fmt:formatNumber value="${donhang.thanhTien}" type="currency" />
									</div>
									<div class="trangthai-don-hang">${donhang.trangThai}</div>
								</a>
							</c:forEach>
						</div>


					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>