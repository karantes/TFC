<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<script>
	$(document)
			.ready(
					function() {
						var password = document.getElementById("password"), confirm_password = document
								.getElementById("confSenha");

						function validatePassword() {
							if (password.value != confirm_password.value) {
								confirm_password
										.setCustomValidity("Senhas diferentes!");
							} else {
								confirm_password.setCustomValidity('');
							}
						}

						password.onchange = validatePassword;
						confirm_password.onkeyup = validatePassword;
					});

	$(function() {
		//Initialize Select2 Elements
		$('.select2').select2()
	})
</script>

<div class="content-wrapper">
	<section class="content-header">
	<div class="row">

		<div class="col-md-12">

			<!-- Profile Image -->
			<div class="box box-primary">
				<div class="box-body box-profile">
					<img class="profile-user-img img-responsive img-circle"
						src="/resources/images/user-image.png" alt="User profile picture">

					<h3 class="profile-username text-center">${usuario.nome }</h3>

					<p class="text-muted text-center">${usuario.email }</p>

					<form:form action="/usuario-update.html" method="POST"
						modelAttribute="usuario">
						<div class="col-xs-12">
							<div class="form-group col-xs-7 col-sm-6 col-lg-8">
								<label>Nome:</label> <br>
								<form:input path="nome" type="text" id="nome"
									class="form-control" value="${usuario.nome }" name="nome"></form:input>
							</div>

							<div class="form-group col-xs-7 col-sm-6 col-lg-8">
								<label>Email:</label> <br>
								<form:input path="email" type="text" id="email"
									class="form-control" value="${usuario.email }" name="email"></form:input>
							</div>

							<security:authorize access="hasAuthority('1')">
								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<label for="sel1">Tipo de usuario:</label>
									<form:select path="tipoUsuario" class="form-control"
										id="tipoUsuario" name="tipoUsuario">
										<form:option value="1">Coordenador de TFC</form:option>
										<form:option value="2">Orientador</form:option>
										<form:option value="3">Aluno</form:option>
									</form:select>
								</div>

								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<form:radiobutton path="ativo" id="ativo" name="ativo"
										value="true" checked="checked" />
									Ativo &nbsp;&nbsp;
									<form:radiobutton path="ativo" id="ativo" name="ativo"
										value="false" />
									Inativo
								</div>
								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<label>Senha:</label> <br> <input type="password"
										id="password" minlength="8" name="password"
										class="form-group col-xs-12"></input>
								</div>

								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<label>Confirme a Senha:</label> <br> <input
										type="password" id="confSenha" name="confSenha"
										class="form-group col-xs-12"></input>
								</div>
							</security:authorize>

							<div class="form-group col-xs-7 col-sm-6 col-lg-8">
								<label>Data de Cadastro:</label> <br> <input type="date"
									class="form-control" value="${usuario.dtCadastro }"
									id="cadastro" name="cadastro" disabled="true"></input>
							</div>
							<div class="form-group col-xs-7 col-sm-6 col-lg-8">
								<label>Data de Alteração:</label> <br> <input
									path="alteracao" type="date" class="form-control"
									value="${usuario.dtAlteracao}" id="alteracao" name="alteracao"
									disabled="true"></input>
							</div>
							<div style="display: none">
								<form:input path="id" type="number" class="form-control"
									value="${usuario.id }" id="id" name="id"></form:input>

								<form:input path="senha" type="password" class="form-control"
									value="${usuario.senha }" id="senha" name="senha"></form:input>

								<form:input path="dtCadastro" type="date" class="form-control"
									value="${usuario.dtCadastro }" id="dtCadastro"
									name="dtCadastro"></form:input>

								<security:authorize access="!hasAuthority('1')">
									<div class="form-group col-xs-7 col-sm-6 col-lg-8">
										<label for="sel1">Tipo de usuario:</label>
										<form:select path="tipoUsuario" class="form-control"
											id="tipoUsuario" name="tipoUsuario">
											<form:option value="1">Coordenador de TFC</form:option>
											<form:option value="2">Orientador</form:option>
											<form:option value="3">Aluno</form:option>
										</form:select>
									</div>

									<div class="form-group col-xs-7 col-sm-6 col-lg-8">
										<form:radiobutton path="ativo" id="ativo" name="ativo"
											value="true" checked="checked" />
										Ativo &nbsp;&nbsp;
										<form:radiobutton path="ativo" id="ativo" name="ativo"
											value="false" />
										Inativo
									</div>
								</security:authorize>

							</div>
						</div>
						<div class="box-footer">
							<button type="submit" class="btn btn-success">Atualizar</button>
						</div>
					</form:form>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</div>

	</div>
	</section>
</div>