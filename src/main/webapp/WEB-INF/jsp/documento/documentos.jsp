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
						<h3 class="box-title">Documentos</h3>
					</div>

					<div class="box-body">
						<div class="box-footer">
							<a
								href='<spring:url value="/documento-register.html"></spring:url>'>
								<button class="btn btn-success">Novo Documento</button>
							</a>
						</div>

						<table id="example1" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>Descrição</th>
									<th>De</th>
									<th>Para</th>
									<th>Tipo</th>
									<th>Data de Envio</th>
									<th>Opções</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${documentos}" var="documento">
									<tr
										${documento.status.equals('NOVO') ? 'style="font-weight: bold;"' : 
											documento.status.equals('CONFIRMADO') ? 'class="label-success"' : '' }>
										<td>${documento.descricao }</td>
										<td>${documento.remetente.nome }</td>
										<td>${documento.destinatario.nome }</td>
										<td>${documento.tipo }</td>
										<td>${documento.dtEnvio}</td>
										<td><a href="${documento.url }"><i
												class="fa fa-cloud-download" data-toggle="tooltip"
												title="Download"></i> </a> <security:authorize
												access="hasAuthority('2')">
												<c:if test="${remetente eq user }">
												&nbsp;<a
														href='<spring:url value="update-documento/${documento.id }.html"></spring:url>'><i
														class="fa fa-check" data-toggle="tooltip"
														title="Apagar Documento"></i> </a>
												</c:if>
											</security:authorize> <c:if
												test="${user.equals(documento.remetente) || user.tipoUsuario == 1 }">
											 &nbsp; <a
													href='<spring:url value="delete-documento/${documento.id }.html"></spring:url>'><i
													class="fa fa-times" data-toggle="tooltip"
													title="Apagar Documento"></i> </a>
											</c:if></td>
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