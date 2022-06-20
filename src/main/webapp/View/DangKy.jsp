<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/View/css/DangKy.css">
    <title>Refood - Đăng ký tài khoản</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
</head>
<body>
<header>
    <jsp:include page="DauTrang.jsp"></jsp:include>
    </header>
    <div class="register" id="register">

        <h1 class="heading">Đăng ký</h1>

        <div class="row">
            <div class="image">
                <img src="<%=request.getContextPath()%>/View/images/login1.jpg" alt="">
            </div>
            <form action="dang-ky" method="post">
				<div class="inputBox">
					<input type="text" name="dang-ky-ho-ten" placeholder="Họ và tên">
					<input type="text" name="dang-ky-sdt" placeholder="Số điện thoại">
					<input type="password" name="dang-ky-matkhau"
						placeholder="Mật khẩu"> 
					<input type="password"
						name="dang-ky-matkhau-nhaplai" placeholder="Nhập lại mật khẩu">
				</div>
				<input type="submit" value="Đăng ký" class="btn">
				<div class="reg-container">
					<a href="dang-nhap" class="btn reg">Đã có tài khoản?</a>
				</div>
				<div class="thong-bao-loi">
				${mess}
				</div>

			</form>
        </div>

    </div>
</body>
</html>