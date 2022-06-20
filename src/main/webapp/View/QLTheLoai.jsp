<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/View/css/Sua.css">
</head>
<body>
	<script type="text/javascript">
		function doDelete(id) {
			if (confirm("Đồng ý xóa thể loại ?? ")) {
				window.location = "xoatheloai?id=" + id;
			}
		}
	</script>
	<jsp:include page="DauTrangQuanTri.jsp"></jsp:include>
	<div class="body">
	<table>
		<tr>
			<th>Mã thể loại</th>
			<th>Tên thể loại</th>

		</tr>
		<c:forEach items="${data}" var="c">
			<tr>
				<td>${c.maTheLoai}</td>
				<td>${c.tenTheLoai }</td>
				<td><a href="#" onclick="sua('${c.tenTheLoai}','${c.maTheLoai}')"
					id="n_sua">Sửa</a></td>
				<td><a href="#" onclick="doDelete('${c.maTheLoai}')">Xóa</a></td>
			</tr>
		</c:forEach>

	</table>
	<div id="them">
		<form action="themtheloai" method="post">
			<h3>Thêm thể loại</h3>
			<label>Tên thể loại: </label><input href="#" type="text"
				name="tenTheLoai" /></br> <input type="submit" value="Lưu" />

		</form>
	</div>
	<div id="sua">
		<form action="capnhattheloai" method="post">
			<h3>Sửa thể loại</h3>
			<label>Mã thể loại: </label> <input id="matheloaimoi" type="text" name="id"
				value="" /></br>
			<label>Tên thể loại: </label> <input id="tentheloaimoi" type="text" name="tenTheLoai"
				value="" /></br> 
		    <input type="submit" value="Lưu" id="n_luu" /></br>
		    
		 <a href="#" onclick="them()" id="n_them">Thêm</a>
		</form>
	</div>
	</div>
	
	<script type="text/javascript">
		function sua(tensp,masp) {
			document.getElementById("sua").style.display = "block";
			document.getElementById("tentheloaimoi").value = tensp;
			document.getElementById("matheloaimoi").value = masp;
			document.getElementById("them").style.display = "none";

		}
		function them() {
			document.getElementById("sua").style.display = "none";
			document.getElementById("them").style.display = "block";

		}
	</script>
</body>
</html>