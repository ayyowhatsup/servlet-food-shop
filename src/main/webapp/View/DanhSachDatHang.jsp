<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết đơn hàng</title>
</head>
<body>
	<jsp:include page="DauTrangQuanTri.jsp"></jsp:include>
	<h3>Khách hàng: ${requestScope.khachhang }</h3>
	<h3>Danh sách đơn hàng</h3>
		<table >
			<tr>
				<th>Mã đơn hàng chi tiet</th>
				<th>Tên sản phẩm</th>
				<th>Số lượng</th>
			</tr>
			<c:forEach items="${data.danhSachVatPham}" var="c">			
				<tr>
					<td>${c.maDonHangChiTiet}</td>
					<td>${c.sanPham.tenSanPham }</td>
					<td>${c.soLuong}</td>
					
				</tr>
			</c:forEach>

		</table>
</body>
</html>