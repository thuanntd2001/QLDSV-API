<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>


<head>

<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>QLDSVTC</title>
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


	<section class="section">
		<div class="row">
			<div class="col-lg-10">

				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Danh sách LTC</h5>
						${message}


						<form:form action="xembaocao/bcdsltc/khoa" method="post"
							modelAttribute="para" class="row g-3">
							

							<div class="col-md-6">
								<label style="margin-left: 13px; margin-top: 15px">Niên
									Khóa :</label>
								<div class="col-md-12">
									<form:input type="text" path="nk" style="margin-left: 10px" />
								</div>
							</div>

							<div class="col-md-6">
								<label style="margin-left: 13px; margin-top: 15px">Học
									kỳ :</label>
								<div class="col-md-12">
									<form:input type="number" path="hk" style="margin-left: 10px" />
								</div>
							</div>

							

							<div class="col-md-12">
								<div class="text-center" style="margin-top: 20px">
									<button type="submit" class="btn btn-primary">Next</button>
									<button type="reset" class="btn btn-secondary">Reset</button>
								</div>
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