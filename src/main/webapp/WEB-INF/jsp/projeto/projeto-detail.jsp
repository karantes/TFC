<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<div class="content-wrapper">
	<section class="content-header">
	<div class="box-header">
		<a href='<spring:url value="/projetos.html"></spring:url>'>
			<button class="btn btn-success">Voltar</button>
		</a>
		<security:authorize access="hasAuthority('1')">
			<a href='<spring:url value="/projeto-register.html"></spring:url>'>
				<button class="btn btn-success">Novo Projeto</button>
			</a>
		</security:authorize>
	</div>

	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">${projeto.nome }</h3>
					&nbsp;
					<c:choose>
						<c:when test="${projeto.ativo }">
							<span class="label label-success">Projeto Ativo</span>
						</c:when>
						<c:otherwise>
							<span class="label label-danger">Projeto Inativo</span>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="box-body">
					<form:form action="/projeto-update.html" method="POST"
						modelAttribute="projeto">
						<div class="form-group col-xs-7 col-sm-6 col-lg-8">
							<label>Projeto</label> <br>
							<form:input path="nome" class="form-group col-xs-12" type="text"
								id="nome" name="nome" value="${projeto.nome }" disabled="true"></form:input>
						</div>
						<div class="form-group col-xs-7 col-sm-6 col-lg-8">
							<label>Semestre</label> <br>
							<form:input path="semestre" class="form-control" type="text"
								id="semestre" name="semestre"
								value="${projeto.semestre.ano }/${projeto.semestre.semestre }° Semestre"
								disabled="true"></form:input>
						</div>
						<div class="form-group col-xs-7 col-sm-6 col-lg-8">
							<label>Descrição</label>
							<form:textarea path="descricao" class="form-control" rows="5"
								cols="50" id="descricao" name="descricao"
								value="${projeto.descricao }" disabled="true" />
						</div>

						<div class="form-group col-xs-7 col-sm-6 col-lg-8">
							<label>Alunos</label>
							<c:forEach items="${projeto.usuarios }" var="orientador">
								<c:if test="${orientador.tipoUsuario eq '3' }">
									<form:input path="usuarios" class="form-control" rows="5"
										cols="50" id="usuarios" name="usuarios"
										value="${orientador.nome }" disabled="true" />
									<br>
								</c:if>
							</c:forEach>
						</div>

						<div class="form-group col-xs-7 col-sm-6 col-lg-8">
							<label>Orientadores</label>
							<c:forEach items="${projeto.usuarios }" var="orientador">
								<c:if test="${orientador.tipoUsuario eq '2' }">
									<form:input path="usuarios" class="form-control" rows="5"
										cols="50" id="usuarios" name="usuarios"
										value="${orientador.nome }" disabled="true" />
									<br>
								</c:if>
							</c:forEach>
						</div>

						<!-- 						<div class="col-xs-12"> -->
						<!-- 							<button type="submit" class="btn btn-success" disabled="true">Atualizar</button> -->
						<!-- 						</div> -->
					</form:form>
				</div>
			</div>
		</div>
	</div>
	</section>
</div>