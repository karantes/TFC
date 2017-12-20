<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- <c:url value="/resources/" /> -->
<style type="text/css">
select {
	width: 100%;
}

.controls {
	width: 100%;
	align: center;
	text-align: center;
}

.controls a {
	/*         background-color: #ff0000; */
	background-color: #3385ff;
	border-radius: 4px;
	border: 2px solid #ff;
	color: #ffffff;
	padding: 2px;
	font-size: 14px;
	text-decoration: none;
	display: inline-block;
	text-align: center;
	margin: 5px;
	width: 30px;
}
</style>
<script>
	function moveAll(from, to) {
		$('#' + from + ' option').remove().appendTo('#' + to);
	}

	function moveSelected(from, to) {
		$('#' + from + ' option:selected').remove().appendTo('#' + to);
	}
	function selectAll(from, to) {
		$("select option").attr("selected", "selected");
		$('#' + from + ' option:selected').remove().appendTo('#' + to);
	}
</script>
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			Usuários <small>Cadastro de Usuários</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i>
					Administração</a></li>
			<li class="active">Usuários</li>
		</ol>
	</section>
	<!-- Main content -->
	<section class="content">
		<div class="row">
			<div class="col-md-8">
				<!-- general form elements -->
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">Cadastro de Conta de Usuário</h3>
					</div>
					<!-- /.box-header -->
					<!-- form start -->
					<form role="form" method="POST">
						<div class="box-body">
							<div class="form-group col-xs-6">
								<label for="name">Nome Completo</label> <input type="text"
									class="form-control" name="name" id="name"
									placeholder="" required>
							</div>
							<div class="form-group col-xs-3">
								<label for="name">Cpf</label> <input type="text"
									class="form-control" name="cpf" id="cpf"
									placeholder="" required>
							</div>
							<div class="form-group col-xs-6">
								<label for="email">E-mail <small> (este e-mail será usado para recuperar sua senha em caso de perdas)</small></label> <input type="text"
									class="form-control" name="email" id="email"
									placeholder="e-mail@e-mail.com " required>
							</div>
							<div class="form-group col-xs-3">
								<label for="password">Senha</label> <input type="password"
									class="form-control" name="password1" id="password1"
									placeholder="Senha " required>
							</div>
							<div class="form-group col-xs-3">
								<label for="password">Repita sua Senha</label> <input type="password"
									class="form-control" name="password2" id="password2"
									placeholder="Repita sua Senha " required>
							</div>
							<div class="form-group col-xs-6">
								<label for="password">Função</label> <input type="text"
									class="form-control" name="profissao" id="profissao"
									placeholder="Função " required>
							</div>
						</div>
						<!-- /.box-body -->
						<div class="box-body">
							<button type="submit" class="btn btn-primary">Cadastrar
								Usuário</button>
						</div>
					</form>
					<div class="box-body pull-right">
						<a href='<spring:url value="/users.html"></spring:url>'>
							<button class="btn btn-primary">Cancelar</button>
						</a>
					</div>
				</div>
				<!-- /.box -->
			</div>
			<!-- /. col-md-6 -->
		</div>
		<!-- /.row -->
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->

