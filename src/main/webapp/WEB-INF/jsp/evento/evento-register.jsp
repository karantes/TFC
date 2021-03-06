<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<link rel="stylesheet"
	href="/resources/plugins/fullcalendar/fullcalendar.min.css"
	media="print">

<script>
	$(function() {
		//Initialize Select2 Elements
		$('.select2').select2()
	})
</script>

<!-- Main content -->
<div class="content-wrapper">
	<section class="content-header">
	<div class="box-header">
		<a href='<spring:url value="/eventos.html"></spring:url>'>
			<button class="btn btn-success">Voltar</button>
		</a>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div class="box box-success">
				<div class="box-header with-border">
					<h3 class="box-title">Cadastro de Eventos</h3>
				</div>
				<div class="box-body">
					<form:form action="/evento-register.html" method="POST"
						modelAttribute="evento">

						<div class="form-group col-xs-7 col-sm-6 col-lg-8">
							<label>Nome do Evento:</label> <br>
							<form:input class="form-group col-xs-12" path="nome" type="text"
								id="nome" name="nome"></form:input>
						</div>
						<div class="form-group col-xs-7 col-sm-6 col-lg-8">
							<label>Descrição do Evento:</label><br>
							<form:textarea class="form-group col-xs-12" path="descricao"
								type="text" id="descricao" rows="5" cols="50" name="descricao" />
						</div>

						<div class="form-group col-xs-7 col-sm-6 col-lg-8">
							<label>Data do Evento:</label> <br>
							<form:input class="form-group col-xs-12" path="dtEvento"
								type="date" id="dtEvento" name="dtEvento"></form:input>
						</div>
						<div class="form-group col-xs-7 col-sm-6 col-lg-8">
							<label>Local do Evento:</label> <br>
							<form:input class="form-group col-xs-12" path="local" type="text"
								id="local" name="local"></form:input>
						</div>

						<security:authorize access="!hasAuthority('3')">
							<div class="form-group col-xs-7 col-sm-6 col-lg-8">
								<label>Participantes:</label> <br> <select
									class="col-xs-12 form-control select2" id="participantesId"
									name="participantesId" multiple="multiple" required>
									<c:forEach items="${participantes }" var="participante">
										<option value="${participante.id }">${participante.nome }</option>
									</c:forEach>
								</select>
							</div>
						</security:authorize>
						<security:authorize access="hasAuthority('3')">
							<input type="hidden" id="participantesId" name="participantesId"
								value="${user.id }">
						</security:authorize>
						<div class="box-footer col-xs-7 col-sm-6 col-lg-8">
							<button type="submit" class="btn btn-success">Cadastrar</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	</section>
</div>

