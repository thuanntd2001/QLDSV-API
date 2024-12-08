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


	<main id="main" class="main">

	<div class="pagetitle">
		<h1>Giảng Viên</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="quanly/pgv/giangvien">Giảng
						Viên</a></li>
				<li class="breadcrumb-item active">Thêm Giảng Viên</li>
			</ol>
		</nav>
	</div>
	<section class="section">
		<div class="row">
			<div class="col-lg-10">

				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Thông tin Giảng Viên</h5>

						${message}

						<form:form action="quanly/pgv/giangvien/add" method="post"
							modelAttribute="item" class="row g-3">
							<div class="col-md-0">

								<form:input type="hidden" class="form-control" id="inputState"
									path="maKhoa" />
							</div>
							<div class="col-md-4">
								<label for="inputEmail5" class="form-label">Mã Giảng
									Viên</label>
								<form:input type="text" class="form-control" id="inputEmail5"
									path="maGV" />
							</div>

							<div class="col-md-4">
								<label for="inputEmail5" class="form-label">Họ Giảng
									Viên</label>
								<form:input type="text" class="form-control" id="inputEmail5"
									path="ho" />
							</div>
							<div class="col-md-8">
								<label for="inputPassword5" class="form-label">Tên Giảng
									Viên</label>
								<form:input type="text" class="form-control" id="inputEmail5"
									path="ten" />
							</div>

							<div class="col-md-4">
								<label for="inputState" class="form-label">Học Hàm</label> <br>
								<form:input type="text" class="form-control" id="inputState"
									path="hocHam" />
								<br>
							</div>
							<div class="col-md-4">
								<label for="inputState" class="form-label">Học vị</label> <br>
								<form:input type="text" class="form-control" id="inputState"
									path="hocVi" />
								<br>
							</div>
							<div class="col-md-4">
								<label for="inputState" class="form-label">Chuyên môn</label> <br>
								<form:input type="text" class="form-control" id="inputState"
									path="chuyenMon" />
								<br>
							</div>



							<div class="col-md-12">
								<div class="text-center" style="margin-top: 20px">
									<button type="submit" class="btn btn-primary">Tạo</button>
									<button type="reset" class="btn btn-secondary">Reset</button>
								</div>
							</div>
						</form:form>
					</div>
				</div>

			</div>
		</div>
	</section>

	</main>
	<!-- End #main -->

	<!-- End #main -->
	<jsp:include page="/template/cn1/pgv/footer.jsp" />


</body>

</html>