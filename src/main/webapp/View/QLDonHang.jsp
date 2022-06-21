<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lí đơn hàng</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/View/css/QuanLy.css">
</head>
<body>
	<script type="text/javascript">
		function doDelete(id) {
			if (confirm("Đồng ý xóa đơn hàng này? ")) {
				window.location = "xoadonhang?id=" + id;
			}
		}
	</script>
	<jsp:include page="DauTrangQuanTri.jsp"></jsp:include>
	<table>
		<tr>
			<th>Mã đơn hàng</th>
			<th>Tên khách hàng</th>
			<th>Địa chỉ nhận hàng</th>
			<th>Danh sách đặt hàng</th>
			<th>Thành tiền</th>
			<th>Trạng thái</th>
		</tr>
		<c:forEach items="${data}" var="c">
			<tr>
				<td>${c.maDonHang}</td>
				<td>${c.khachHang.tenNguoiDung }</td>
				<td>${c.diaChiNhanHang}</td>
				<td><a href="qldonhangchitiet?id=${c.maDonHang }">Danh sách
						đặt hàng</a>
				<td>${c.thanhTien}</td>
				<td>${c.trangThai}</td>
				<td><c:if test="${c.trangThai eq 'Đặt hàng thành công'}">
						<a href="capnhatdonhang?id=${c.maDonHang}">Xác nhận hoàn thành</a>
					</c:if> &nbsp;&nbsp;&nbsp;&nbsp; <a href="#"
					onclick="doDelete('${c.maDonHang}')">Xóa</a></td>

			</tr>
		</c:forEach>

	</table>
</body>
</html>