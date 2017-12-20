<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="content-wrapper">
	<section class="content-header">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">Usuario ${usuario.nome }</h3>
				</div>
				<div class="box-body">
					<div class="col-xs-7 col-sm-6 col-lg-8">
						<a href='<spring:url value="/usuarios.html"></spring:url>'>
							<button class="btn btn-success">Voltar</button>
						</a>
					</div>
					<div class="col-xs-7 col-sm-6 col-lg-8">
						<a href='<spring:url value="/usuario-register.html"></spring:url>'>
							<button class="btn btn-success">Novo Usuario</button>
						</a>
					</div>
				</div>
				<div class="box-body">

					<div class="box-footer">
						<div class="col-xs-12">
							<form:form action="/usuario-register.html" method="POST"
								modelAttribute="usuario">
								<div class="col-xs-12">
									<div class="form-group col-xs-7 col-sm-6 col-lg-8">
										<c:choose>
											<c:when test="${usuario.ativo }">
												<span class="label label-default">Usuario Ativo</span>
											</c:when>
											<c:otherwise>
												<span class="label label-default">Usuario Inativo</span>
											</c:otherwise>
										</c:choose>
									</div>
									<div class="form-group col-xs-7 col-sm-6 col-lg-8">
										<label>Nome:</label> <br>
										<form:input path="nome" type="text" id="nome"
											value="${usuario.nome }" name="nome" disabled="true"></form:input>
									</div>

									<div class="form-group col-xs-7 col-sm-6 col-lg-8">
										<label>Email:</label> <br>
										<form:input path="email" type="text" id="email"
											value="${usuario.email }" name="email" disabled="true"></form:input>
									</div>

									<div class="form-group col-xs-7 col-sm-6 col-lg-8">
										<label>Tipo de Usuario:</label> <br>
										<form:input path="tipoUsuario" type="text"
											value="${usuario.gotTipo() }" id="tipoUsuario"
											name="tipoUsuario" disabled="true"></form:input>
									</div>

									<div class="form-group col-xs-7 col-sm-6 col-lg-8">
										<label>Data de Cadastro:</label> <br>
										<form:input path="dtCadastro" type="date"
											value="${usuario.dtCadastro }" id="dtCadastro"
											name="dtCadastro" disabled="true"></form:input>
									</div>

									<div class="form-group col-xs-7 col-sm-6 col-lg-8">
										<label>Data de Alteração:</label> <br>
										<form:input path="dtAlteracao" type="date"
											value="${usuario.dtAlteracao}" id="dtAlteracao"
											name="dtAlteracao" disabled="true"></form:input>
									</div>
								</div>
								<div class="box-footer">
									<button type="submit" class="btn btn-success">Atualizar</button>
								</div>
							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>
</div>