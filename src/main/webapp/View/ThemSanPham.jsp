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
<form action="themsanpham" method="post">
            <h3>Thêm sản phẩm</h3>
            <label>Tên sản phẩm: </label><input type="text" name="tenSanPham"/></br> 
            <label>Giá tiền: </label><input type="text" name="giaTien"/></br> 
            <label>Miêu tả: </label><input type="text" name="mieuTa"/></br> 
            <label>Hình ảnh: </label><input type="text" name="hinhAnh"/></br>
            <label>Đơn vị tính: </label> <input type="text" name="donViTinh"></br>
            <label>Số lượng: </label><input type="text" name="tonKho"></br>
            <label>Thể loại: </label><select name="theloai">
            <c:forEach items="${dstheloai}" var="c">	
            		<option value="${c.maTheLoai }">${c.tenTheLoai }</option>
            		</c:forEach>
            </select>
            <input type="submit" value="Lưu"/>
        </form>
</body>
</html>