<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta path="viewport" content="width=device-width, initial-scale=1.0">
<title>UIS</title>

<jsp:include page="/template/cn1/khoa/head.jsp" />

</head>

<body>


	<jsp:include page="/template/cn1/khoa/header.jsp" />
	<jsp:include page="/template/cn1/khoa/sidebar.jsp" />





	<main id="main" class="main"> <!-- ======= Content ======= -->
	<div class="container-fluid main">
		<div class="main-content">
			<div class="header-row">
				<h4>Nhập điểm</h4>
			</div>


			<div class="header-column">
				<h5>Danh sách sinh viên</h5>
			</div>



			<form:form action="nhapdiem/nhapdiem/khoa" method="POST"
				modelAttribute="itemListWrapper">
				<c:forEach var="item" items="${itemListWrapper.lst}"
					varStatus="status">
					<div class="row">
						<div class="col-md-0">
							<form:input type="hidden" class="form-control"
								id="maLTC_${status.index}" path="lst[${status.index}].maLTC"
								  readonly="true"/>
						</div>
						<div class="col-md-2">
							<div class="form-group">
								<label for="maSV_${status.index}">MASV</label>
								<form:input type="text" class="form-control"
									id="maSV_${status.index}" path="lst[${status.index}].maSV" readonly="true"/>
							</div>
						</div>
						<div class="col-md-2">
							<div class="form-group">
								<label for="hoTen_${status.index}">HOTEN</label>
								<form:input type="text" class="form-control"
									id="hoTen_${status.index}" path="lst[${status.index}].hoTen" readonly="true"/>
							</div>
						</div>
						<div class="col-md-2">
							<div class="form-group">
								<label for="diemCC_${status.index}">CC</label>
								<form:input type="number" class="form-control"
									id="diemCC_${status.index}" path="lst[${status.index}].diemCC" />
							</div>
						</div>
						<div class="col-md-2">
							<div class="form-group">
								<label for="diemGK_${status.index}">GK</label>
								<form:input type="number" class="form-control"
									id="diemGK_${status.index}" path="lst[${status.index}].diemGK" />
							</div>
						</div>
						<div class="col-md-2">
							<div class="form-group">
								<label for="diemCK_${status.index}">CK</label>
								<form:input type="number" class="form-control"
									id="diemCK_${status.index}" path="lst[${status.index}].diemCK" />
							</div>
						</div>
						<div class="col-md-1">
							<div class="form-group">
								<label for="diemCK_${status.index}">TK</label>
								<form:input type="text" class="form-control"
									id="diemCK_${status.index}" path="lst[${status.index}].diemTK" readonly="true"/>
							</div>
						</div>
					</div>
				</c:forEach>

				<div class="form-group text-center">
					<button type="submit" class="btn btn-primary btn-block">LƯU</button>
				</div>
			</form:form>
		</div>
	</div>
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