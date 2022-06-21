<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>Refood - Website bán thực phẩm sạch số 1 Việt Nam</title>
    <LINK REL="stylesheet" HREF="<%=request.getContextPath()%>/View/css/TrangChu.css" TYPE="text/css"> 
</head>

<body>


    <header>
    <jsp:include page="DauTrang.jsp"></jsp:include>
    </header>
	
	<!-- header section ends -->

    <!-- home section starts  -->

	<section class="trangchuz" id="trangchuz">

		<div class="noidung">
            <h3>Food made with love</h3>
            <p>Refood là trang web thực phẩm sạch uy tín, cam kết đem lại cho gia đình bạn những bữa cơm chất lượng, dinh dưỡng và đảm bảo an toàn!</p>
            <a href="san-pham" class="nutdathang">Đặt hàng</a>
        </div>

        <div class="hinhanh">
            <img src="<%=request.getContextPath()%>/View/images/home-img.png" alt="">
        </div>

    </section>
    

    <jsp:include page="CuoiTrang.jsp"></jsp:include>
</body>

</html>