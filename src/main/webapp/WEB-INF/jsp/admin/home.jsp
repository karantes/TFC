<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- <c:url value="/resources/" /> -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->

	<!-- Main content -->
	<div class="content body">

		<section id="introduction">
		<h2 class="page-header">
			<a href="#introduction">Introdução</a>
		</h2>
		<p class="lead">
			${appIntro}
		</p>
		
		<h2 class="page-header">
			<a href="#introduction">${appIntroTitle1}</a>
		</h2>
		<p class="lead">
			${appIntroDesc1}
		</p>
		</section>
		
		<h2 class="page-header">
			<a href="#introduction">${appIntroTitle2}</a>
		</h2>
		<p class="lead">
			${appIntroDesc2}
		</p>
		</section>
		
		<h2 class="page-header">
			<a href="#introduction">${appIntroTitle3}</a>
		</h2>
		<p class="lead">
			${appIntroDesc3}
		</p>
		</section>
		
		<h2 class="page-header">
			<a href="#introduction">${appIntroTitle4}</a>
		</h2>
		<p class="lead">
			${appIntroDesc4}
		</p>
		</section>
		
	</div>
</div>
<!-- /.content-wrapper -->

