<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!-- <c:url value="/resources/" /> -->

<script src="//code.jquery.com/jquery.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<!-- Content Header (Page header) -->


<body class="hold-transition login-page">

	<div class="login-box">

		<c:if test="${param.error eq true}">
			<div class="alert alert-warning alert-dismissible">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				<h4>
					<i class="icon fa fa-warning"></i> Atenção!
				</h4>
				Usuario ou senha incorretos!
			</div>
		</c:if>

		<c:if test="${param.authenticate eq false}">
			<div class="alert alert-danger alert-dismissible">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				<h4>
					<i class="icon fa fa-warning"></i> Atenção!
				</h4>
				Você precisa estar logado para acessar o sistema!
			</div>
		</c:if>

		<div class="login-logo">

			<a href="#"><b>TFC</b></a>
		</div>
		<!-- /.login-logo -->

		<div class="login-box-body">
			<p class="login-box-msg">Preencha para iniciar</p>
			<form action="/login" method="POST">
				<div class="form-group has-feedback">
					<input type="text" name="username" id="username"
						class="form-control" placeholder="Email" required="true"
						autocomplete="off"> <span
						class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" name="password" id="password"
						class="form-control" placeholder="Senha" required="true">
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
					<div class="col-xs-8"></div>
					<!-- /.col -->
					<div class="col-xs-4">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" class="form-control" />
						<button type="submit" class="btn btn-primary btn-block btn-flat">Logar</button>
					</div>

					<!-- /.col -->
				</div>
			</form>
		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->
	<!-- /.content -->
	<!-- /.content-wrapper -->