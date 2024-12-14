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

<jsp:include page="/template/cn1/sv/head.jsp" />

</head>
<body>

	<!-- ======= Header ======= -->
	<jsp:include page="/template/cn1/sv/header.jsp" />

	<!-- End Header -->

	<!-- ======= Sidebar ======= -->
	<jsp:include page="/template/cn1/sv/sidebar.jsp" />

	<!-- End Sidebar-->


	<main id="main" class="main">

	<div class="pagetitle">
		<h1>Môn học</h1>
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
									<th scope="col">Mã Môn học</th>
									<th scope="col">Tên Môn học</th>
									<th scope="col">Số SV đăng ký</th>
									<th scope="col">Nhóm</th>
									<th scope="col">Giảng viên</th>
									<th scope="col">TKB</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${lst }" var="item">
									<tr>

										<th scope="row">${item.maMH}</th>
										<td>${item.tenMH}</td>
										<td>${item.soSVDK}</td>
										<td>${item.nhom}</td>
										<td>${item.GV}</td>
										<td>${item.TKB}</td>

										<td><form:form action="dadangky/sv" method="post"
												modelAttribute="para" class="row g-3">
												<form:input type="hidden" path="maLTC" value="${item.maLTC}" />												
												<form:input type="hidden" path="maMH" value="${item.maMH}" />
												<form:input type="hidden" path="tenMH" value="${item.tenMH}" />
												<form:input type="hidden" path="soSVDK" value="${item.soSVDK}" />
												<form:input type="hidden" path="nhom" value="${item.nhom}" />
												<form:input type="hidden" path="GV" value="${item.GV}" />
												<form:input type="hidden" path="huy" value="true" />
												<form:input type="hidden" path="TKB" value="${item.TKB}" /><pre>  </pre>
												<button type="submit" class="btn btn-secondary">Hủy Đăng
													Ký</button>
											</form:form></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- End Table with stripped rows -->
						<a href="quanly/pgv/monhoc/add" class="fa fa-plus"></a>
					</div>
				</div>

			</div>
		</div>
	</section>

	</main>
	<!-- End #main -->
	<jsp:include page="/template/cn1/sv/footer.jsp" />


</body>

</html>