<%@page import="com.group3.Model.TheLoai"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<LINK REL="stylesheet"
	HREF="<%=request.getContextPath()%>/View/css/KhoSanPham.css"
	TYPE="text/css">
<title>Refood - Sản phẩm</title>

</head>

<body>
	<header>
		<jsp:include page="DauTrang.jsp"></jsp:include>
	</header>
	<section class="bosuutap" id="bosuutap">

		<h1 class="tieudemuc">Sản phẩm</h1>
		<div class="bosuutap-wrap">

			<c:if test="${tuKhoa!=null}">
				<h1 class="de-muc-tim-kiem">Kết quả tìm kiếm cho từ khóa '${tuKhoa}'</h1>
				
			</c:if>
			<c:if test="${tuKhoa==null}">
				<div class="bosuutap-nav">
					<ul class="danhsachnav">
						<h2>DANH MỤC</h2>
						<c:forEach items="${danhSachTheLoai}" var="theLoai">
							<li
								class='<c:if test="${theLoai.maTheLoai == mtl}">dang-duoc-lua-chon</c:if>'><i
								class="fa fa-arrow-circle-right" aria-hidden="true"></i> <a
								href="san-pham?maTheLoai=${theLoai.maTheLoai}"> <c:out
										value="${theLoai.tenTheLoai}"></c:out>
							</a></li>
						</c:forEach>
					</ul>
				</div>
			</c:if>



			<div class="danhsachsanphamm">
				<c:forEach items="${danhSachSanPham}" var="sanPham">
					<div class="osanpham">
						<c:if test="${sanPham.tonKho >0 && sanPham.tonKho<=10}">
							<div class="tag-so-luong-san-pham">SỐ LƯỢNG CÓ HẠN</div>
						</c:if>
						<c:if test="${sanPham.tonKho == 0 }">
							<div class="tag-so-luong-san-pham">HẾT HÀNG</div>
						</c:if>
						<img src="${sanPham.hinhAnh}" alt="">
						<div class="ttsanpham">
							<h3>${sanPham.tenSanPham}</h3>
							<p>${sanPham.mieuTa}</p>
							<h3>
								<fmt:setLocale value="vi_VN" />
								<fmt:formatNumber value="${sanPham.giaTien}" type="currency" />
								/1 ${sanPham.donViTinh}
							</h3>
							<c:if test="${sanPham.tonKho > 0 }">
								<a href="them-vao-gio-hang?maSanPham=${sanPham.maSanPham}"
									class="nutdatmua">Đặt mua</a>
							</c:if>

						</div>
					</div>
				</c:forEach>


			</div>
		</div>



	</section>
</body>

</html>