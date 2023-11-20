<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>진행 중인 문서 전체</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />

<!-- css, js -->
<link rel="stylesheet" href="/css/electronicsignature/documentTotal.css">
<script src="/js/electronicsignature/documentTotal.js"></script>
</head>
<body>
	<%@ include file="../commons/header.jsp" %>
	
	<div class="container">
		<%@ include file="../electronicsignature/electronicsignatureNaviBar.jsp" %>
		
		<div class="container__wait">
			<div class="wait__checkboxList">
				<label>
					<input type="checkbox" class="checkboxList__checkbox"/>
				</label>
				<span>
					<a href="#" id="anchorApprovalForm">보기: 모든 문서</a>
					<i class="fa-solid fa-angle-down"></i>
				</span>
				<div class="approvalForm__dropMenu">
					<li><a value="0">모든 문서</a></li>
					<li><a value="1">지출 결의서</a></li>
					<li><a value="2">휴가 사유서</a></li>
				</div>
			</div>
			
			<div class="wrap__documentTable">
				<div class="documentTable__header">
					<div id="documentTable__Allcheckbox">
						<label><input type="checkbox"/></label>
					</div>
					<div id="documentTable__favorites"></div>
					<div>문서 번호</div>
					<div>제목</div>
					<div>기안자</div>
					<div>기안일</div>
					<div>완료일</div>
					<div>문서 종류</div>
					<div>구분</div>
				</div>
				
				<div class="documentTable__body">
					<div class="document__list">
						<div class="documentTable__checkbox">
							<label><input type="checkbox" /></label>
						</div>
						<div class="documentTable__favorites">
							<i class="fa-regular fa-star"></i>
						</div>
						<div class="documentTable__documentSeq">GA-업연-20231117-0001</div>
						<div class="documentTable__title">
						<div class="title">1212131</div>
							<i class="fa-solid fa-paperclip fileIcon"></i>
						</div>
						<div class="documentTable__drafter">김이사</div>
						<div class="documentTable__draftDate">2023-11-17</div>
						<div class="documentTable__dueDate">2023-11-17</div>
						<div class="documentTable__documentCategory">품의서</div>
						<div class="documentTable__division">기안</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>