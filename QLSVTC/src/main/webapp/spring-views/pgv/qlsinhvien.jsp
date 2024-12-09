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
		<h1>Sinh viên</h1>
	</div>
	<!-- End Page Title -->

	<section class="section">
		<div class="row">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-body">

						<!-- Bordered Table -->
						<table class="table table-bordered">
							<thead>
								<tr>
									<th scope="col">Mã Sinh viên</th>
									<th scope="col">Họ</th>

									<th scope="col">Tên</th>
									<th scope="col">Phái</th>
									<th scope="col">Địa chỉ</th>
									<th scope="col">Ngày sinh</th>
									<th scope="col">Tên Chuyên ngành</th>
									<th scope="col">Password</th>


									<th scope="col">Thao tác</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${lst}" var="item">
									<tr>

										<th scope="row">${item.maSV}</th>
										<td>${item.ho}</td>

										<td>${item.ten}</td>
										<td><c:if test="${item.phai == true}"> Nam </c:if> <c:if
												test="${item.phai == false}">Nữ </c:if></td>
										<td>${item.diaChi}</td>
										<td>${item.ngaySinh}</td>
										<td>${item.tenCN}</td>

										<td>${item.password}</td>

										<td><a href="quanly/pgv/sinhvien/edit?id=${item.maSV}"
											class="fa fa-pencil-square-o"></a> <a
											href="quanly/pgv/sinhvien/xoa?id=${item.maSV}"
											class="fa fa-times-circle"></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- End Table with stripped rows -->
						<a href="quanly/pgv/sinhvien/add" class="fa fa-plus"></a>
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