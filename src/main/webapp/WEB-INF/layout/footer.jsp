<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="now" value="<%=new java.util.Date()%>" />
<footer class="main-footer">
	<!-- To the right -->
	<div class="pull-right hidden-xs">Sys - TFC.</div>
	<!-- Default to the left -->
	<%-- <strong>Copyright &copy; <fmt:formatDate pattern="yyyy" value="${now}" /> <a href="#">JSI</a>.</strong> All rights reserved.
        <strong>Current Version:</strong> 0.1 Released <fmt:formatDate pattern="yyyy" value="${now}" />. Next Update: TBA --%>
</footer>