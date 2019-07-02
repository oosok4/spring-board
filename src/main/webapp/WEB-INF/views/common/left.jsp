<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	

<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		<li class="active"><a href="${cp }/createBoard">게시판 만들기 <spanclass="sr-only"></span></a></li>
	<br>
	<c:forEach items="${boardList}" var="board">
			<li class="active"><a href="${cp}/primaryBoard?board_id=${board.board_id}&&userid=${USER_INFO.userId}">${board.board_name}</a></li>
	</c:forEach>
	</ul>
</div>
