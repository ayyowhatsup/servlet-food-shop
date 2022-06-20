<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/View/css/DangNhap.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
    <title>Refood - Đăng nhập</title>
</head>

<body>
    <header>
    <jsp:include page="DauTrang.jsp"></jsp:include>
    </header>
    <div class="login" id="login">

        <h1 class="heading">Đăng nhập</h1>

        <div class="row">
            <div class="image">
                <img src="<%=request.getContextPath()%>/View/images/login1.jpg" alt="">
            </div>
            <form action="<%=request.getContextPath()%>/dang-nhap" method="post">
                <div class="inputBox">
                    <input type="text" name="dang-nhap-sdt" placeholder="Số điện thoại">
                    <input type="password" name="dang-nhap-mat-khau" placeholder="Mật khẩu">
                </div>
                <input type="submit" value="Đăng nhập" class="btn">
				<div class="reg-container">
					<a href="<%=request.getContextPath()%>/dang-ky" class="btn reg">Tạo tài khoản mới</a>
				</div>
				<div class="thong-bao-loi">
				${mess}
				</div>

			</form>
        </div>

    </div>
</body>

</html>