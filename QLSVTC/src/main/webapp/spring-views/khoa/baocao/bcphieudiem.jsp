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

	<main id="mains" class="mains"> <!-- End Page Title -->

	<section class="section">
		<div class="row">
			<div class="col-lg-12">
				<div class="card">
					<div class="card-body">
						<h4
							style="margin: 20px 0; text-align: center; text-transform: uppercase;">
							<b>CỘNG HOÀ XÃ HỘI CHỦ NGHĨA VIỆT NAM</b>
						</h4>
						<h5 style="margin: 20px 0; text-align: center;">
							<b>Độc Lập - Tự Do - Hạnh Phúc</b>
						</h5>
						<br>
						<h6 style="margin-left: 550px;">............,ngày.....tháng.....năm
							20....</h6>
						<br> <br>
						<h3
							style="margin: 20px; text-align: center; text-transform: uppercaguse">
							<strong> Phiếu điểm </strong>

						</h3>

						<h5 style="margin: 20px; text-align: center">
							<b>Khoa : ${TENKHOA}</b>
						</h5>
						<br> <br>
						<p style="margin: 20px; text-align: right"></p>
						<br>
						<p style="margin: 20px; text-align: center">Mã Sinh Viên:
							${para.maSV}</p>

						<table class="table table-bordered border-bottom-0 border-dark">
							<thead class="reports head">
								<tr>
									<th scope="col">STT</th>
									<th scope="col">Tên Môn Học</th>
									<th scope="col">Điểm Hết Môn</th>

								</tr>
							</thead>
							<tbody class="reports body">
								<c:forEach items="${lst }" var="item" varStatus="status">
									<tr>

										<th scope="row">${status.index + 1}</th>
										<td>${item.tenMonHoc}</td>
										<td>${item.diemHetMon}</td>


									</tr>
								</c:forEach>

							</tbody>
						</table>
						<br> <br>
						<p
							style="margin: 20px; text-align: center; text-transform: uppercaguse">
							<b> Người lập báo cáo</b> <br> <br> <br> <br>
							<br> <br> <b>${USERMODEL.hoTen}</b>

						</p>
						<br> <br> <br> <br>

					</div>
				</div>

			</div>
		</div>
	</section>

	</main>

</body>

</html>