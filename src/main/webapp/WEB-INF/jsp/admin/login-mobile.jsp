<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- <c:url value="/resources/" /> -->

<script src="//code.jquery.com/jquery.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<!-- Content Header (Page header) -->

<body class="hold-transition login-page">

	<div class="login-box">
		<div class="login-logo">

			<a href="#"><b>${appName}</b> - Mobile</a>
		</div>
		<!-- /.login-logo -->
		<c:if test="${param.passwordIgual eq false}">
			<div class="alert alert-danger">
				<a class="close" data-dismiss="alert" aria-label="close"
					title="close">×</a> <strong>Senhas informadas não são
					iguais.</strong>
			</div>
		</c:if>
		<c:if test="${param.error eq true}">
			<div class="alert alert-danger">
				<a href="#" class="close" data-dismiss="alert">x</a> <strong>Informações
					informadas não estão corretas, por favor verifique seus dados e
					tente novamente.</strong>
			</div>
		</c:if>
		<c:if test="${param.encontrouCpf eq false}">
			<div class="alert alert-danger">
				<a class="close" data-dismiss="alert" aria-label="close"
					title="close">×</a> <strong>CPF não encontrado.</strong>
			</div>
		</c:if>
		<c:if test="${param.pref eq true}">
			<div class="alert alert-success">
				<a class="close" data-dismiss="alert" aria-label="close"
					title="close">×</a> <strong>Password redefinido com
					sucesso.</strong>
			</div>
		</c:if>

		<div class="login-box-body">
			<p class="login-box-msg">Preencha para iniciar</p>

			<!-- 			<form action="/jsi/login" method="POST"> -->
			<form action="/login" method="POST">
				<div class="form-group has-feedback">
					<input type="text" name="username" id="username"
						class="form-control" placeholder="CPF (SOMENTE NUMEROS)"
						required="true" autocomplete="off"> <span
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
				
				<input type="hidden" name="isMobile" id="isMobile"
						class="form-control">
				
			</form>
	
	
			<c:if test="${appName != 'JSI - Real Jurídica' }">
   				<a
				href='<spring:url value="/user-password-recovery.html"></spring:url>'
				class="text-center">Novo Usuário</a> <br>
			</c:if>
			

			<a
				href='<spring:url value="/user-password-recovery.html"></spring:url>'
				class="text-center">Esqueci minha senha</a> <br>
			<%-- <a
				href='<spring:url value="register.html"></spring:url>'
				class="text-center">Ainda não tenho uma conta</a> --%>

		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->


	<!-- /.content -->
	<!-- /.content-wrapper -->