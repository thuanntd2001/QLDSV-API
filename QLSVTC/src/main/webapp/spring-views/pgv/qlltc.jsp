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
		<h1>Lớp tín chỉ - NKHK: ${MANKHK}</h1>
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
									<th scope="col">Mã Lớp tín chỉ</th>
									<th scope="col">Môn Học</th>

									<th scope="col">Nhóm</th>
									<th scope="col">Số SV tối thiểu</th>


									<th scope="col">Thao tác</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${lst }" var="item">
									<tr>

										<th scope="row">${item.maLTC}</th>
										<td>${item.tenMH}</td>

										<td>${item.nhom}</td>
										<td>${item.soSVToiThieu}</td>


										<td><a href="quanly/pgv/ltc/edit?id=${item.maLTC}"
											class="fa fa-pencil-square-o"></a> <a
											href="quanly/pgv/ltc/xoa?id=${item.maLTC}"
											class="fa fa-times-circle"></a><a
											href="quanly/pgv/giangday?idltc=${item.maLTC}&idmh=${item.maMH}&nhom=${item.nhom}"
											class="fa fa-plus"></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- End Table with stripped rows -->
						<a href="quanly/pgv/ltc/add" class="fa fa-plus"></a>
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