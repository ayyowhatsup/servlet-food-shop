<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>API Documentation</title>
</head>
<body>
	<h1>API Documentation</h1>
	
	<h2>Sản phẩm</h2>
	<h3>Lấy Tất cả sản phẩm </h3>
	<div>GET http://localhost:1314/FoodShop1.0/api/san-pham</div>
	<h3>Lấy sản phẩm theo mã sản phẩm</h3>
	<div>GET http://localhost:1314/FoodShop1.0/api/san-pham/1</div>
	<h3>Lấy sản phẩm theo mã thể loại</h3>
	<div>GET http://localhost:1314/FoodShop1.0/api/san-pham/the-loai/1</div>
	<h3>Tạo sản phẩm mới</h3>
	<div>POST http://localhost:1314/FoodShop1.0/api/san-pham</div>
	<h3>Xóa sản phẩm</h3>
	<div>DELETE http://localhost:1314/FoodShop1.0/api/san-pham/1</div>
	<h3>Sửa sản phẩm</h3>
	<div>PUT http://localhost:1314/FoodShop1.0/api/san-pham/1</div>
	
	<h2>Thể Loại</h2>
	<h3>Lấy danh sách thể loại</h3>
	<div>GET http://localhost:1314/FoodShop1.0/api/the-loai</div>
	<h3>Lấy thể loại cụ thể</h3>
	<div>GET http://localhost:1314/FoodShop1.0/api/the-loai/1</div>
	<h3>Xóa thể loại</h3>
	<div>DELETE http://localhost:1314/FoodShop1.0/api/the-loai/1</div>
	<h3>Sửa thể loại</h3>
	<div>PUT http://localhost:1314/FoodShop1.0/api/the-loai/1</div>
	<h3>Thêm thể loại</h3>
	<div>POST http://localhost:1314/FoodShop1.0/api/the-loai</div>
	
	<h2>Đơn hàng</h2>
	<h3>Lấy tất cả đơn hàng</h3>
	<div>GET http://localhost:1314/FoodShop1.0/api/don-hang</div>
	<h3>Lấy đơn hàng theo mã đơn hàng</h3>
	<div>GET http://localhost:1314/FoodShop1.0/api/don-hang/1</div>
	<h3>Lấy đơn hàng theo người dùng</h3>
	<div>GET http://localhost:1314/FoodShop1.0/api/don-hang/nguoi-dung/1</div>
	<h3>Xóa đơn hàng</h3>
	<div>DELETE http://localhost:1314/FoodShop1.0/api/don-hang/1</div>
	<h3>Tạo đơn hàng mới</h3>
	<div>POST http://localhost:1314/FoodShop1.0/api/don-hang</div>
	
	<h2>Người Dùng</h2>
	<h3>Lấy tất cả người dùng</h3>
	<div>GET http://localhost:1314/FoodShop1.0/api/nguoi-dung</div>
	<h3>Lấy người dùng theo mã</h3>
	<div>GET http://localhost:1314/FoodShop1.0/api/nguoi-dung/1</div>
	<h3>Tạo mới người dùng</h3>
	<div>POST http://localhost:1314/FoodShop1.0/api/</div>
	<h3>Xóa người dùng</h3>
	<div>http://localhost:1314/FoodShop1.0/api/</div>
	
	
	<h2>Đăng nhập</h2>
	<div>POST http://localhost:1314/FoodShop1.0/api/dang-nhap</div>
</body>
</html>