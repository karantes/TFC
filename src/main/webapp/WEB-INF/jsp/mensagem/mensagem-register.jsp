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
				<div>

					<div class="box-header">
						<h3 class="box-title">Cadastro de Mensagens</h3>
					</div>

					<div class="box-body">
						<div class="box-footer">
							<a href='<spring:url value="/mensagens.html"></spring:url>'>
								<button class="btn btn-success">Voltar</button>
							</a>
						</div>
						<form:form action="/mensagem-register.html" method="POST"
							modelAttribute="Mensagem">
							<div class="col-xs-12">
								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<label>Assunto da Mensagem:</label> <br>
									<form:input path="assunto" type="text" id="assunto"
										name="assunto"></form:input>
								</div>

								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<label>Texto da Mensagem:</label><br>
									<form:textarea path="mensagem" type="text" id="mensagem"
										rows="5" cols="50" name="mensagem" />
								</div>


								<!-- 								<div class="form-group col-xs-7 col-sm-6 col-lg-8"> -->
								<!-- 									<label>Participantes:</label> <br> <select -->
								<!-- 										class="form-control" id="participantesId" -->
								<!-- 										name="participantesId" multiple="multiple"> -->
								<%-- 										<c:forEach items="${participantes }" var="participante"> --%>
								<%-- 											<option value="${participante.id }">${participante.nome }</option> --%>
								<%-- 										</c:forEach> --%>
								<!-- 									</select> -->
								<!-- 								</div> -->
							</div>
							<div class="box-footer ">
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
<!-- /.content-wrapper -->