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
						<h3 class="box-title">Cadastro de Usuários</h3>
					</div>
					<!-- /.box-header -->
					<!-- form start -->
					<form role="form" method="POST">
						<div class="box-body">
							<div class="form-group col-xs-6">
								<label for="name">Nome do Usuário</label> <input type="text"
									class="form-control" name="name" id="name"
									placeholder="Nome do Usuário " required>
							</div>
							<div class="form-group col-xs-6">
								<label for="email">E-mail</label> <input type="text"
									class="form-control" name="email" id="email"
									placeholder="E-mail " required>
							</div>
							<div class="form-group col-xs-6">
								<label>Centro de custo</label> <select class="form-control"
									id="centroDeCusto" name="idCentroDeCusto">
									<!-- centro de custo contabil -->
									<c:forEach items="${centrosDeCusto}" var="centroDeCusto">
										<option value="${centroDeCusto.id}"><c:out
												value="${centroDeCusto.nome}"></c:out>
									</c:forEach>
								</select>
							</div>
							<div class="form-group col-xs-6">
								<label for="password">Senha</label> <input type="password"
									class="form-control" name="password" id="password"
									placeholder="Senha " required>
							</div>

							<div class="form-group col-xs-6">
								<label for="password">Profissão</label> <input type="text"
									class="form-control" name="profissao" id="profissao"
									placeholder="Profissão " required>
							</div>

							<div class="form-group col-xs-6">
								<label for="nome">Data Inclusão <small>(mm/dd/aaaa)</small></label>
								<input type="date" name="anoInclusao" class="form-control"
									value="${hoje}" />
							</div>
							<div class="form-group col-xs-12">
								<label>Roles:</label>
							</div>
							<div class="form-group col-xs-12">
								<div class="col-xs-4">
									<select multiple size="10" id="listaTodasRoles">
										<c:forEach var="role" items="${roles}">
											<option value="${role.id}">${role.id}-${role.name}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-xs-4">
									<div class="controls">
										<br> <a
											href="javascript:selectAll('listaTodasRoles', 'listaRolesSelecionadas')">&gt;&gt;</a>
										<br> <a
											href="javascript:moveSelected('listaTodasRoles', 'listaRolesSelecionadas')">&gt;</a>
										<br> <a
											href="javascript:moveSelected('listaRolesSelecionadas', 'listaTodasRoles')">&lt;</a>
										<br> <a
											href="javascript:moveAll('listaRolesSelecionadas', 'listaTodasRoles')"
											href="#">&lt;&lt;</a> <br>
									</div>
								</div>
								<div class="col-xs-4">
									<select multiple id="listaRolesSelecionadas" size="10"
										name="listaRolesSelecionadas[]"></select>
								</div>

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

