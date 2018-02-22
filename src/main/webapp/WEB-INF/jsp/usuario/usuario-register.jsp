<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script>
	$(document)
			.ready(
					function() {
						var password = document.getElementById("senha"), confirm_password = document
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
		<div class="col-xs-12">
			<div class="box">
				<div>

					<div class="box-header">
						<h3 class="box-title">Cadastro de Usuarios</h3>
					</div>

					<div class="box-body">
						<div class="box-footer">
							<a href='<spring:url value="/usuarios.html"></spring:url>'>
								<button class="btn btn-success">Voltar</button>
							</a>
						</div>
						<form:form action="/usuario-register.html" method="POST"
							modelAttribute="Usuario">
							<div class="col-xs-12">
								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<label>Nome:</label> <br>
									<form:input path="nome" type="text" id="nome" name="nome"
										required="required"></form:input>
								</div>

								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<label>Senha:</label> <br>
									<form:input path="senha" type="password" id="senha"
										minlength="8" name="senha" required="required"></form:input>
								</div>

								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<label>Confirme a Senha:</label> <br> <input path="senha"
										type="password" id="confSenha" name="confSenha"
										required="required"></input>
								</div>

								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<label>Email:</label> <br>
									<form:input path="email" type="email" id="email" name="email"
										required="required"></form:input>
								</div>

								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<label for="sel1">Tipo de usuario:</label>
									<form:select path="tipoUsuario" class="form-control"
										id="tipoUsuario" name="tipoUsuario">
										<form:option value="1">Coordenador de TFC</form:option>
										<form:option value="2">Orientador</form:option>
										<form:option value="3">Aluno</form:option>
									</form:select>
								</div>

								<div class="form-group checkbox col-xs-3">
									<label><form:checkbox path="ativo" id="ativo"
											name="ativo" value="true" checked="checked"></form:checkbox>Ativo</label>
								</div>
							</div>
							<div class="box-footer">
								<button type="submit" class="btn btn-success">Cadastrar</button>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>
</div>