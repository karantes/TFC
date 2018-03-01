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

		<div class="col-md-12">

			<!-- Profile Image -->
			<div class="box box-primary">
				<div class="box-body box-profile">
					<img class="profile-user-img img-responsive img-circle"
						src="/resources/images/user-image.png" alt="User profile picture">

					<h3 class="profile-username text-center">${usuario.nome }</h3>

					<p class="text-muted text-center">${usuario.email }</p>

					<form:form action="/usuario-register.html" method="POST"
						modelAttribute="usuario">
						<div class="col-xs-12">
							<div class="form-group col-xs-7 col-sm-6 col-lg-8">
								<label>Nome:</label> <br>
								<form:input path="nome" type="text" id="nome"
									class="form-control" value="${usuario.nome }" name="nome"
									disabled="true"></form:input>
							</div>

							<div class="form-group col-xs-7 col-sm-6 col-lg-8">
								<label>Email:</label> <br>
								<form:input path="email" type="text" id="email"
									class="form-control" value="${usuario.email }" name="email"
									disabled="true"></form:input>
							</div>

							<div class="form-group col-xs-7 col-sm-6 col-lg-8">
								<label>Tipo de Usuario:</label> <br>
								<form:input path="tipoUsuario" type="text" class="form-control"
									value="${usuario.gotTipo() }" id="tipoUsuario"
									name="tipoUsuario" disabled="true"></form:input>
							</div>

							<div class="form-group col-xs-7 col-sm-6 col-lg-8">
								<label>Data de Cadastro:</label> <br>
								<form:input path="dtCadastro" type="date" class="form-control"
									value="${usuario.dtCadastro }" id="dtCadastro"
									name="dtCadastro" disabled="true"></form:input>
							</div>

							<div class="form-group col-xs-7 col-sm-6 col-lg-8">
								<label>Data de Alteração:</label> <br>
								<form:input path="dtAlteracao" type="date" class="form-control"
									value="${usuario.dtAlteracao}" id="dtAlteracao"
									name="dtAlteracao" disabled="true"></form:input>
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

		<!-- 		<div class="col-xs-9"> -->
		<!-- 			<div class="box"> -->
		<!-- 				<div class="box-header"> -->
		<!-- 					<h3 class="box-title">Usuario</h3> -->
		<!-- 				</div> -->
		<!-- 				<div class="box-body"> -->
		<!-- 					<div class="col-xs-7 col-sm-6 col-lg-8"> -->
		<%-- 						<a href='<spring:url value="/usuarios.html"></spring:url>'> --%>
		<!-- 							<button class="btn btn-success">Voltar</button> -->
		<!-- 						</a> -->
		<!-- 					</div> -->
		<!-- 					<div class="col-xs-7 col-sm-6 col-lg-8"> -->
		<%-- 						<a href='<spring:url value="/usuario-register.html"></spring:url>'> --%>
		<!-- 							<button class="btn btn-success">Novo Usuario</button> -->
		<!-- 						</a> -->
		<!-- 					</div> -->
		<!-- 				</div> -->
		<!-- 				<div class="box-body"> -->

		<!-- 					<div class="box-footer"> -->
		<!-- 						<div class="col-xs-12"> -->

		<!-- 						</div> -->
		<!-- 					</div> -->
		<!-- 				</div> -->
		<!-- 			</div> -->
		<!-- 		</div> -->
	</div>
	</section>
</div>