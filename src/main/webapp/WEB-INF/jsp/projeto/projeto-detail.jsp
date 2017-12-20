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
					<h3 class="box-title">Projeto ${projeto.nome }</h3>
				</div>
				<div class="box-body">
					<div class="col-xs-7 col-sm-6 col-lg-8">
						<a href='<spring:url value="/projetos.html"></spring:url>'>
							<button class="btn btn-success">Voltar</button>
						</a>
					</div>
					<div class="col-xs-7 col-sm-6 col-lg-8">
						<a href='<spring:url value="/projeto-register.html"></spring:url>'>
							<button class="btn btn-success">Novo Projeto</button>
						</a>
					</div>
				</div>
				<div class="box-body">

					<div class="box-footer">
						<div class="col-xs-12">
							<form:form action="/projeto-register.html" method="POST"
								modelAttribute="projeto">
								<div class="col-xs-12">
									<div class="form-group col-xs-7 col-sm-6 col-lg-8">
										<h4 class="form-group col-xs-7 col-sm-6 col-lg-8">
											<c:choose>
												<c:when test="${projeto.ativo }">
													<span class="label label-default">Projeto Ativo</span>
												</c:when>
												<c:otherwise>
													<span class="label label-default">Projeto Inativo</span>
												</c:otherwise>
											</c:choose>
										</h4>
										<br> <label>Projeto</label> <br>
										<form:input path="nome" type="text" id="nome" name="nome"
											value="${projeto.nome }" disabled="true"></form:input>
									</div>
									<div class="form-group col-xs-7 col-sm-6 col-lg-8">
										<label>Descrição</label>
										<form:textarea path="descricao" class="form-control" rows="5"
											cols="50" id="descricao" name="descricao"
											value="${projeto.descricao }" disabled="true" />
									</div>
								</div>
								<div class="box-footer">
									<button type="submit" class="btn btn-success" disabled="true">Atualizar</button>
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