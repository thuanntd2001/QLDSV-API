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

	<div class="pagetitle">
		<h1>Gợi ý chuyên ngành</h1>
	</div>
	<!-- End Page Title -->

	<section class="section">
		<div class="row">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-body">
					<br>
					<br>
						<h4>Tổng CNPM: ${sumCNPM}, Tổng HTTT: ${sumHTTT} </h4>
						<br>
						<h3>Chuyên ngành gợi ý: ${nganh}</h3>
						<!-- Bordered Table -->
						<table class="table table-bordered">
							<thead>
								<tr>
									<th scope="col">Mã Môn học</th>
									<th scope="col">Tên Môn học</th>
									<th scope="col">Số tín chỉ</th>
									<th scope="col">Tỷ Lệ Thực hành</th>

									<th scope="col">Kỹ thuật</th>
									<th scope="col">Phân tích</th>
									<th scope="col">Thiết kế</th>
									<th scope="col">Kỹ năng nhóm</th>

									<th scope="col">Điểm Chuyên cần</th>
									<th scope="col">Điểm Tổng kết</th>
									<th scope="col">Chuyên ngành gợi ý tương ứng</th>


									<!-- <th scope="col">Số SV đăng ký</th>
									<th scope="col">Giảng viên</th>
									<th scope="col">TKB</th> -->
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${lst }" var="item">
									<tr>

										<td>${item.maMH}</td>
										<td>${item.tenMH}</td>
										<td>${item.soTC}</td>
										<td>${item.tyLeTH}</td>
										<td>${item.kyThuat}</td>
										<td>${item.phanTich}</td>
										<td>${item.thietKe}</td>
										<td>${item.kyNangNhom}</td>
										<td>${item.diemCC}</td>
										<td>${item.diemTK}</td>
										<th scope="row">${item.chuyenNganh}</th>

									</tr>
								</c:forEach>
							</tbody>
						</table>




					</div>
				</div>

			</div>
		</div>
	</section>

	</main>
	<!-- End #main -->



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