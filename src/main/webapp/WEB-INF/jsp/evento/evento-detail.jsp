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
			<a href='<spring:url value="/eventos.html"></spring:url>'>
				<button class="btn btn-success">Voltar</button>
			</a> <a href='<spring:url value="/evento-register.html"></spring:url>'>
				<button class="btn btn-success">Novo Evento</button>
			</a>
			<div class="box">
				<div class="box-header">
					<h3 class="box-title">${evento.nome }</h3>
				</div>

				<div class="box-body">
					<div class="col-xs-12">
						<form:form action="/evento-update.html" method="POST"
							modelAttribute="evento">

							<div class="form-group col-xs-7 col-sm-6 col-lg-8">
								<label>Nome do Evento</label> <br>
								<form:input path="nome" type="text" id="nome" name="nome"
									value="${evento.nome }" disabled="true"></form:input>
							</div>
							<div class="form-group col-xs-7 col-sm-6 col-lg-8">
								<label>Descrição do Evento</label><br>
								<form:textarea path="descricao" type="text" id="descricao"
									rows="5" cols="50" name="descricao"
									value="${evento.descricao }" disabled="true" />
							</div>

							<div class="form-group col-xs-7 col-sm-6 col-lg-8">
								<label>Data do Evento</label> <br>
								<form:input path="dtEvento" type="date" id="dtEvento"
									name="dtEvento" value="${evento.dtEvento }" disabled="true"></form:input>
							</div>
							<div class="form-group col-xs-7 col-sm-6 col-lg-8">
								<label>Local do Evento</label> <br>
								<form:input path="local" type="text" id="local" name="local"
									value="${evento.local }" disabled="true"></form:input>
							</div>
							<div class="box-footer col-xs-7 col-sm-6 col-lg-8">
								<button type="submit" class="btn btn-success" disabled="true">Alterar</button>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>
</div>