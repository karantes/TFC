<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<div class="content-wrapper">
	<section class="content-header">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div>

					<div class="box-header">
						<h3 class="box-title">Projetos</h3>
					</div>

					<div class="box-body">
						<div class="box-header with-border">
							<security:authorize access="hasAuthority('1')">
								<a
									href='<spring:url value="/projeto-register.html"></spring:url>'>
									<button class="btn btn-success">Novo Projeto</button>
								</a>
							</security:authorize>
						</div>

						<table id="example1" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>Projeto</th>
									<th>Descrição</th>
									<th>Ativo</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${projetos}" var="projeto">
									<tr>
										<td>${projeto.nome}</td>
										<td>${projeto.descricao }</td>
										<td><c:choose>
												<c:when test="${projeto.ativo }">
													<span class="label label-success">Projeto Ativo</span>
												</c:when>
												<c:otherwise>
													<span class="label label-danger">Projeto Inativo</span>
												</c:otherwise>
											</c:choose></td>
										<td><a
											href='<spring:url value="/projeto-detail/${projeto.id}.html"></spring:url>'
											title="Gerenciar Projeto, visualizar detalhes.."><span
												class="glyphicon glyphicon-list"></span></a> &nbsp; &nbsp; <security:authorize
												access="hasAuthority('1')">
												<c:if test="${projeto.ativo eq true}">
													<a
														href='<spring:url value="/desativar-projeto/${projeto.id}.html"></spring:url>'
														title="Desativar Projeto"><span
														class="fa fa-times-circle"></span></a>
												</c:if>
												<c:if test="${projeto.ativo eq false}">
													<a
														href='<spring:url value="/ativar-projeto/${projeto.id}.html"></spring:url>'
														title="Ativar Projeto"><span
														class="fa fa-check-circle"></span></a>
												</c:if>
											</security:authorize></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>
</div>