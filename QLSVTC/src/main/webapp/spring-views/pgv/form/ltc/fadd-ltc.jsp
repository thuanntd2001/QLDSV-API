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
		<h1>Lớp Tín Chỉ</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="quanly/pgv/ltc">Lớp
						Tín Chỉ</a></li>
				<li class="breadcrumb-item active">Thêm Lớp Tín Chỉ</li>
			</ol>
		</nav>
	</div>
	<section class="section">
		<div class="row">
			<div class="col-lg-10">

				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Thông tin Lớp Tín Chỉ</h5>

						${message}

						<form:form action="quanly/pgv/ltc/add" method="post"
							modelAttribute="item" class="row g-3">
					


						<div class="col-md-10">
								<label for="inputEmail5" class="form-label">Môn học</label> <br>
								<form:select class="selectpicker" path="maMH">
									<c:forEach items="${lstMH}" var="item">
										<option value="${item.maMH}">${item.maMH}-
											${item.tenMH}</option>
									</c:forEach>
								</form:select>

							</div>


							<div class="col-md-4">
								<label for="inputState" class="form-label">Số SV tối
									thiểu</label> <br>
								<form:input type="text" class="form-control" id="inputState"
									path="soSVToiThieu" />
								<br>
							</div>
							
							<div class="col-md-8">
								<label for="inputPassword5" class="form-label">Nhóm</label>
								<form:input type="text" class="form-control" id="inputEmail5"
									path="nhom" />
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