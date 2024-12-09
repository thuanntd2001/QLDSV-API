<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>


<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>UIS</title>

<jsp:include page="/template/cn1/pgv/head.jsp" />

</head>
<body>

	<!-- ======= Header ======= -->
	<jsp:include page="/template/cn1/pgv/header.jsp" />

	<!-- End Header -->

	<!-- ======= Sidebar ======= -->
	<jsp:include page="/template/cn1/pgv/sidebar.jsp" />

	<!-- End Sidebar-->

	<main id="main" class="main"> <!-- End Sidebar-->
	<div class="pagetitle">
		<h1>Nhân viên</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="quanly/pgv/monhoc">Nhân viên</a></li>
				<li class="breadcrumb-item active">Xóa Nhân viên</li>
			</ol>
		</nav>
	</div>
	<section class="section">
		<div class="row">
			<div class="col-lg-5">

				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Xóa Nhân viên</h5>
						${message} 
						
						<form action="quanly/pgv/nhanvien/xoa?id=${id }" method="post"
							class="row g-3">
							<div  class="col-md-12" style="align-items=center;">
								<label for="inputEmail5" class="form-label">Bạn có đồng ý xoá Nhân viên ${id }</label>
							</div>
							
							<div class="col-md-12">
								<label for="inputEmail5" class="form-label">Xác Nhận( Nhập "YES" vào khung này)</label>
								<input type="text" class="form-control" name="xacNhan" />
							</div>
							
							<div class="text-center" style="margin-top:15px; margin-left:170px">
								<button type="submit" class="btn btn-primary">Xoá</button>
								
							</div>
						</form>
						<!-- End Multi Columns Form -->

					</div>
				</div>

			</div>
		</div>
	</section>

	</main>
	<!-- End #main -->
	<jsp:include page="/template/cn1/pgv/footer.jsp" />


</body>

</html>