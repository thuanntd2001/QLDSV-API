<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>UIS</title>

<jsp:include page="/template/cn1/sv/head.jsp" />

</head>

<body>


	<jsp:include page="/template/cn1/sv/header.jsp" />
	<jsp:include page="/template/cn1/sv/sidebar.jsp" />



	<main id="main" class="main">
	<div class="header-content d-flex justify-content-center">
		<h3>THÔNG TIN TÀI KHOẢN</h3>
	</div>
	<section class="section profile">
		<div class="row">
			<div class="col-xl-4">

				<div class="card">
					<form:form modelAttribute="user"
						class="card-body profile-card pt-4 d-flex flex-column align-items-center">

						<h2>${sessionScope.USERMODEL.hoTen }</h2>
						<h3>${sessionScope.USERMODEL.maNV }</h3>
						<h3>Nhóm: ${sessionScope.USERMODEL.tenNhom }</h3>

					</form:form>
				</div>

			</div>
			<div class="col-xl-8">
				<div class="card">
					<div class="card-body pt-3">
						<!-- Bordered Tabs -->
						<ul class="nav nav-tabs nav-tabs-bordered">

							<li class="nav-item">
								<button class="nav-link active" data-bs-toggle="tab"
									data-bs-target="#profile-overview">Thông Tin</button>
							</li>



							<li class="nav-item">
								<button class="nav-link" data-bs-toggle="tab"
									data-bs-target="#profile-change-password">Đổi Mật Khẩu</button>
							</li>

							<li class="nav-item">
								<button class="nav-link" data-bs-toggle="tab"
									data-bs-target="#avata-edit">Đổi ảnh đại diện</button>
							</li>
						</ul>
						<div class="tab-content pt-2">
							<div class="tab-pane fade show active profile-overview"
								id="profile-overview">
								<form:form modelAttribute="nv,user" method="post">
									<h5 class="card-title">Thông Tin Cá Nhân</h5>
									<div class="row">
										<div class="col-lg-3 col-md-4 label ">Mã Sinh Viên</div>
										<div class="col-lg-9 col-md-8">${sessionScope.USERMODEL.maNV}
										</div>
									</div>

									<div class="row">
										<div class="col-lg-3 col-md-4 label">Họ Tên</div>
										<div class="col-lg-9 col-md-8">${sessionScope.USERMODEL.hoTen }</div>
									</div>

									<div class="row">
										<div class="col-lg-3 col-md-4 label">Vai trò</div>
										<div class="col-lg-9 col-md-8">${sessionScope.USERMODEL.tenNhom }</div>
									</div>

									<div class="row">
										<div class="col-lg-3 col-md-4 label">Tên Tài Khoản</div>
										<div class="col-lg-9 col-md-8">${sessionScope.username }</div>
									</div>
									<div class="row">
										<div class="col-lg-3 col-md-4 label">Khoa</div>
										<div class="col-lg-9 col-md-8">${USERMODEL.khoa }</div>
									</div>

								</form:form>
							</div>


							<div class="tab-pane fade pt-3" id="profile-change-password">
								<!-- Change Password Form -->

								<!-- End Change Password Form -->


							</div>
							<!-- ICON Tabs -->
							<div class="tab-pane fade pt-3" id="avata-edit">
								<label> ${message1} </label>
								<form action="user-avt.htm" method="post"
									enctype="multipart/form-data" class="row mb-3">
									<label for="profileImage"
										class="col-md-4 col-lg-3 col-form-label">Ảnh Đại Diện</label>

								</form>
							</div>
						</div>


					</div>
				</div>

			</div>
		</div>
	</section>

	</main>


	<jsp:include page="/template/cn1/sv/footer.jsp" />

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js">
		
	</script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>