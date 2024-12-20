<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>



<!-- ======= Header ======= -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="#">QUẢN LÝ ĐIỂM SINH VIÊN</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNavDropdown" style="text-align:right !important;">
		<ul class="navbar-nav">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#"
				id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false">	 Xin chào ${USERMODEL.hoTen}
			</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" href="#">Thông tin</a> <a
						class="dropdown-item" href="#">Công cụ</a> <a
						class="dropdown-item" href="dang-nhap?action=logout">Đăng xuất</a>
				</div></li>
			<li class="nav-item active"><a class="nav-link" href="#">
					<span class="sr-only">(current)</span>
			</a></li>
			

			<li class="nav-item"><a class="nav-link" href="dang-nhap?action=logout">Đăng xuất</a></li>
		 
		</ul>
		<c:if test="${not empty message}">
					<div class="alert alert-${alert}">${message}</div>
				</c:if>
	</div>
</nav>
