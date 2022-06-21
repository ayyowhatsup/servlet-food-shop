<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Cập nhật sản phẩm</title>
<meta charset="UTF-8">
</head>
<body>
<jsp:include page="DauTrangQuanTri.jsp"></jsp:include>
<form action="capnhatsanpham" method="post">
<c:set var="c" value="${requestScope.sanpham}"/>
Mã sản phẩm: <input type="text" name="maSanPham" readonly value="${c.maSanPham }"/></br>
            Tên sản phẩm: <input type="text" name="tenSanPham" value="${c.tenSanPham }"/></br> 
            Giá tiền: <input type="text" name="giaTien" value="${c.giaTien }"/></br> 
            Miêu tả: <input type="text" name="mieuTa" value="${c.mieuTa }"/></br> 
            Hình ảnh: <input type="text" name="hinhAnh" value="${c.hinhAnh }"></br>
            Đơn vị tính: <input type="text" name="donViTinh" value="${c.donViTinh }"></br>
            Số lượng: <input type="text" name="tonKho" value="${c.tonKho }"></br>
            Thể loại: <select name="theloai">
            <c:forEach items="${dstheloai}" var="d">	
            		<option value="${d.maTheLoai }" 
            		<c:if test="${c.theLoai.maTheLoai == d.maTheLoai }">selected</c:if>
            		>${d.tenTheLoai }</option>
            		</c:forEach>
            </select>
            <input type="submit" value="Lưu"/>
        </form>
</body>
</body>
</html>