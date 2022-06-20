<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
	function doDelete(id) {
		if (confirm("Đồng ý xóa người dùng? ")) {
			window.location = "xoanguoidung?id=" + id;
		}
	}
</script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/View/css/QuanLy.css">
</head>
<body>
	<jsp:include page="DauTrangQuanTri.jsp"></jsp:include>
	<table>
		<tr>
			<th>Mã người dùng</th>
			<th>Tên người dùng</th>
			<th>Số điện thoại</th>
			<th>Chức năng</th>

		</tr>
		<c:forEach items="${data}" var="c">
			<tr>
				<td>${c.maNguoiDung}</td>
				<td>${c.tenNguoiDung}</td>
				<td>${c.soDienThoai}</td>
				<td><c:if test="${c.laQuanTriVien == 1 }">Quản lý</c:if> <c:if
						test="${c.laQuanTriVien == 0 }">Khách hàng</c:if></td>
				<td><a href="capnhatnguoidung?id=${c.maNguoiDung}">Sửa</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" onclick="doDelete('${c.maNguoiDung}')">Xóa</a></td>
			</tr>
		</c:forEach>


	</table>
</body>
</html>