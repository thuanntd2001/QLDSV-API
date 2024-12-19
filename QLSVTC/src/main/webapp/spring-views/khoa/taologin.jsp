<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>



<head>

<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>UIS</title>
<!-- ======= Head ======= -->
<jsp:include page="/template/cn1/khoa/head.jsp" />

</head>
<body>

	<!-- ======= Header ======= -->
	<jsp:include page="/template/cn1/khoa/header.jsp" />

	<!-- End Header -->

	<!-- ======= Sidebar ======= -->
	<jsp:include page="/template/cn1/khoa/sidebar.jsp" />

	<!-- End Sidebar-->
	<main id="main" class="main">

	<div class="pagetitle">
		<h1>Tạo tài khoản</h1>
	</div>
	<!-- End Page Title -->
	<section class="section">
		<div class="row">
			<div class="col-lg-8">

				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Tạo tài khoản giảng viên</h5>

						<!-- Multi Columns Form -->
						<form:form action="taologin/khoa" method="post"
							modelAttribute="newLogin" class="row g-3">
							<div class="col-md-6">
								<div class="col-md-8">
									<label for="inputCity" class="form-label">Mã giảng viên</label>

									<form:select class="selectpicker" path="quyen">
										<c:forEach items="${lstGV}" var="item">
											<option value="${item.maGV}">${item.maGV} -
												${item.ho} - ${item.ten}</option>
										</c:forEach>
									</form:select>
								</div>
							</div>

							<div class="col-md-6">
								<div class="col-md-8">
									<label for="inputCity" class="form-label">Quyền</label>
									<form:select path="quyen">
										<form:option value="KHOA"></form:option>
									</form:select>
								</div>
							</div>

							<div class="col-md-10">
								<label for="inputPassword5" class="form-label">Tên tài
									khoản</label>
								<form:input type="text" class="form-control" id="inputPassword5"
									path="lgName" />
							</div>


							<div class="col-md-10">
								<label for="inputPassword5" class="form-label">Mật khẩu</label>
								<form:input type="password" class="form-control"
									id="inputPassword5" path="pass" />
							</div>

							<br>

							<h6>${message1}</h6>
							<br>
							<br>
							<br>
							<br>
							<br>
							<div class="text-center">
								<button type="submit" class="btn btn-primary">Accept</button>
								<button type="reset" class="btn btn-secondary">Reset</button>
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
	<jsp:include page="/template/cn1/khoa/footer.jsp" />


</body>

</html>