<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lí sản phẩm</title>
<script type="text/javascript">
	function doDelete(id) {
		if (confirm("Đồng ý xóa sản phẩm ")) {
			window.location = "xoasanpham?id=" + id;
		}
	}
</script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/View/css/QuanLy.css">
</head>
<body>
	<jsp:include page="DauTrangQuanTri.jsp"></jsp:include>
	<h3>
		<a href="themsanpham">Thêm sản phẩm</a>
	</h3>
	<table>
		<tr>
			<th>Mã sản phẩm</th>
			<th>Tên sản phẩm</th>
			<th>Giá tiền</th>
			<th>Miêu tả</th>
			<th>Hình ảnh</th>
			<th>Đơn vị tính</th>
			<th>Tồn kho</th>
			<th>Thể loại</th>
		</tr>
		<c:forEach items="${data}" var="c">
			<tr>
				<td>${c.maSanPham}</td>
				<td>${c.tenSanPham}</td>
				<td>${c.giaTien}</td>
				<td>${c.mieuTa}</td>
				<td>${c.hinhAnh}</td>
				<td>${c.donViTinh }</td>
				<td>${c.tonKho }</td>
				<td>${c.theLoai.tenTheLoai }</td>

				<td><a href="capnhatsanpham?id=${c.maSanPham}">Sửa</a>&nbsp;&nbsp;
					<a href="#" onclick="doDelete('${c.maSanPham}')">Xóa</a></td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>