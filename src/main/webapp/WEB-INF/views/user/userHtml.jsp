<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

name : <input type="text" readonly value="${userVo.name }"/><br>
alias : <input type="text" readonly value="${userVo.alias }" /><br>
birth : <input type="text" readonly value="<fmt:formatDate value="${userVo.birth }" pattern="yyyy-MM-dd"/>"  /><br>