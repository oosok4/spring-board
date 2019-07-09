<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- core라이브러리를 쓸꺼니까! uri 잘 확인 -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>게시글 상세조회</title>
<style>
.td {
	border: 1px solid black;
}
</style>

<script>
	$(document).ready(function() {
		$('#btn').on('click', function() {
			if ($('#com').val().length > 500) {
				$('#com').val($('#com').val().substring(0, 500));
				alert("500자 이내로 입력해주세요");
			} else if ($('#com').val().length == 0) {
				alert("등록할 댓글을 입력해주세요");
			} else {
				$('#frm1').submit();
			}
		});

		$('.del').on('click', function() {
			var parent = $(this).parent();
			parent.empty();
			parent.append('삭제된 게시물입니다.');

		});

		$('#btn2').on('click', function() {
			$('#frm2').submit();
		});

	});
</script>
</head>

<body>

	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<div class="row">
			<div class="col-sm-8 blog-main">
				<h2 class="sub-header">게시글 상세정보</h2>

				<form id="frm" class="form-horizontal" role="form"
					action="${pageContext.request.contextPath }/modify" method="get">

					<input type="hidden" id="text_id" name="text_id"
						value="${textVo.text_id}">

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">제목&nbsp;&nbsp;:</label>
						<div class="col-sm-10">
							<label class="control-label">${textVo.text_title }</label> <input
								type="hidden" id="text_title" name="text_title"
								value="${textVo.text_title }">
						</div>
					</div>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">내용&nbsp;&nbsp;:</label>
						<div class="col-sm-10">
							<label class="control-label">${textVo.text_content }</label> <input
								type="hidden" id="text_content" name="text_content"
								value="${textVo.text_content }" />
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">파일&nbsp;&nbsp;:</label>
						<c:forEach items="${fileList}" var="file">
							<a
								href="${pageContext.request.contextPath}/fileDownload?file_id=${file.file_id}">${file.file_name}</a>
							<br>
						</c:forEach>
					</div>

					<c:choose>
						<c:when test="${textVo.userid != usersVo.userId}">
						</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose>
				</form>


				<form id="frm1" class="form-horizontal" role="form"
					action="${pageContext.request.contextPath }/comment" method="get">
					<div class="form-group">
						<div class="col-sm-10">
							<input type="hidden" id="text_id" name="text_id"
								value="${textVo.text_id}"> <input type="hidden"
								id="userid" name="userid" value="${textVo.userid}">

							<textarea id="com" name="com_content" row="15" cols="120"></textarea>
							<button id="btn" type="button">댓글 저장!</button>
						</div>
					</div>
				</form>

				<!-- 답글 GG -->
				<form id="frm2" class="form-horizontal" role="form"
					action="${pageContext.request.contextPath }/reply" method="get">
					<div class="form-group">
						<div class="col-sm-10">
							<button id="btn2" type="button">답글작성</button>
							<input type="hidden" id="text_id" name="text_id"
								value="${textVo.text_id}"> <input type="hidden"
								id="userid" name="userid" value="${usersVo.userId}"> <input
								type="hidden" id="board_id" name="board_id"
								value="${textVo.board_id}"> <input type="hidden"
								id="group_num" name="group_num" value="${textVo.group_num}">
						</div>
					</div>
				</form>



				<dl>
					<c:forEach items="${comList}" var="com">
						<dd class="here">
							${com.com_content} <br> 작성자 : ${com.userid}
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="del" type="button">삭제</button>
							<br> 작성시간 : ${com.com_date}

							<hr>
						</dd>
					</c:forEach>
				</dl>
				<div class="form-group">
					<div class="col-sm-2">
						<button type="submit">
							<a 
							href="${pageContext.request.contextPath }/modify?text_id=${textVo.text_id}&&userid=${textVo.userid}&&board_id=${textVo.board_id}">
								modify</a>
						
						</button>
					</div>

					<div class="col-sm-2">
						<button type="button">
							<a
								href="${pageContext.request.contextPath }/delete?text_id=${textVo.text_id}&&userid=${textVo.userid}&&board_id=${textVo.board_id}">
								delete</a>
						</button>
					</div>
				</div>

			</div>
		</div>
	</div>