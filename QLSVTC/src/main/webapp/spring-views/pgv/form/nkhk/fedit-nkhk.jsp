<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>


<head>

<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

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


	<main id="main" class="main">

	<div class="pagetitle">
		<h1>Niên khóa - Học kỳ</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="quanly/pgv/nkhk">Niên khóa - Học kỳ</a></li>
				<li class="breadcrumb-item active">Sửa Niên khóa - Học kỳ</li>
			</ol>
		</nav>
	</div>
	<section class="section">
		<div class="row">
			<div class="col-lg-10">

				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Thông tin</h5>
						${message}
						<!-- Multi Columns Form -->
						<form:form action="quanly/pgv/nkhk/edit"
							method="post" modelAttribute="item" class="row g-3">
							
							<div class="col-md-0">
								
								<form:input type="hidden" class="form-control" id="inputEmail5"
									path="maNKHK" />
							</div>

							<div class="col-md-4">
								<label for="inputEmail5" class="form-label">Năm học</label>
								<form:input type="text" class="form-control" id="inputEmail5"
									path="namHoc" />
							</div>
							<div class="col-md-4">
								<label for="inputState" class="form-label">Học kỳ</label> <br>
								<form:input path="hk" />
								<br>
							</div>	
							<div class="col-md-4">
								<label for="inputState" class="form-label">Ngày mở đăng ký</label> <br>
								<form:input path="ngayMoDK" />
								<br>
							</div>
								<div class="col-md-4">
								<label for="inputState" class="form-label">Ngày đóng đăng ký</label> <br>
								<form:input path="ngayDongDK" />
								<br>
						
						

							<div class="col-md-12" >
								<div class="text-center" style="margin-top:20px">
									<button type="submit" class="btn btn-primary">Sửa</button>
									<button type="reset" class="btn btn-secondary">Reset</button>
								</div>
							</div>
						</form:form>
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