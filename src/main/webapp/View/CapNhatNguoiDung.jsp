<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/View/css/Them.css">
</head>
<body>
<jsp:include page="DauTrangQuanTri.jsp"></jsp:include>
	<form action="capnhatnguoidung" method="post">
		<c:set var="c" value="${requestScope.nguoidung}" />
		<label>Mã người dùng:</label> <input type="text" name="maNguoiDung"
			readonly value="${c.maNguoiDung }" /></br> <label>Tên người dùng:
		</label><input type="text" name="tenNguoiDung" value="${c.tenNguoiDung}" /></br> <label>Số
			điện thoại :</label> <input type="text" name="soDienThoai"
			value="${c.soDienThoai }" /></br> <label>Chức năng:</label> <select
			name="laQuanTriVien">
			<option value="1"
				<c:if test="${c.laQuanTriVien == 1 }">selected</c:if>>Quản
				lý</option>
			<option value="0"
				<c:if test="${c.laQuanTriVien == 0 }">selected</c:if>>Khách
				Hàng</option>
		</select> </br> <input type="submit" value="Lưu" />
	</form>
</body>
</html>