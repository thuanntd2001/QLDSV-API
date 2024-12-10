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

<jsp:include page="/template/cn1/khoa/head.jsp" />

</head>

<body>


	<jsp:include page="/template/cn1/khoa/header.jsp" />
	<jsp:include page="/template/cn1/khoa/sidebar.jsp" />






	<main id="main" class="main">
	<div class="header-content d-flex justify-content-center">
		<h3>Nhập điểm SV</h3>
	</div>
	<section class="section">
		<div class="row">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-body">

						<!-- Bordered Table -->
						<form action="submitScores.jsp" method="post">
							<table>
								<tr>
									<th>Tên Sinh Viên</th>
									<th>?i?m Môn 1</th>
									<th>?i?m Môn 2</th>
									<th>?i?m Môn 3</th>
								</tr>
								<tr>
									<td><input type="text" name="studentName1" required></td>
									<td><input type="number" name="score1_1" min="0" max="10"
										step="0.1" required></td>
									<td><input type="number" name="score1_2" min="0" max="10"
										step="0.1" required></td>
									<td><input type="number" name="score1_3" min="0" max="10"
										step="0.1" required></td>
								</tr>
								<tr>
									<td><input type="text" name="studentName2" required></td>
									<td><input type="number" name="score2_1" min="0" max="10"
										step="0.1" required></td>
									<td><input type="number" name="score2_2" min="0" max="10"
										step="0.1" required></td>
									<td><input type="number" name="score2_3" min="0" max="10"
										step="0.1" required></td>
								</tr>
								<!-- Thêm nhi?u hàng cho sinh viên khác n?u c?n -->
							</table>
							<br> <input type="submit" value="G?i">
						</form>
						<!-- End Table with stripped rows -->
						<a href="quanly/pgv/giangvien/add" class="fa fa-plus"></a>
					</div>
				</div>

			</div>
		</div>
	</section>
	</main>



	<jsp:include page="/template/cn1/khoa/footer.jsp" />

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js">
		
	</script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>