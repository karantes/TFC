
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="content-wrapper">
	<section class="content-header">
	<div class="box-header">
		<a href='<spring:url value="/mensagem-register.html"></spring:url>'>
			<button class="btn btn-success">Nova Mensagem</button>
		</a>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div>

					<div class="box-header">
						<h3 class="box-title">Mensagens</h3>
					</div>

					<div class="box-body">
						<table id="example1" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>De</th>
									<th>Para</th>
									<th>Assunto</th>
									<th>Data</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${mensagens}" var="mensagem">
									<tr
										${mensagem.status.equals('NOVA') ? 'style="font-weight: bold;"' : '' }>
										<td>${mensagem.remetente.nome}</td>
										<td>${mensagem.destinatario.nome}</td>
										<td>${mensagem.assunto}</td>
										<td><fmt:formatDate value="${mensagem.dtEnvio}"
												pattern="dd/MM/yyyy" /></td>
										<td><a
											href='<spring:url value="/mensagem-detail/${mensagem.id}.html"></spring:url>'
											data-toggle="tooltip" title="Visualizar mensagem..."><span
												class="glyphicon glyphicon-list"></span></a> <c:if
												test="${user.equals(mensagem.remetente) || user.tipoUsuario == 1 }">
											 &nbsp; <a
													href='<spring:url value="delete-mensagem/${mensagem.id }.html"></spring:url>'><i
													class="fa fa-times" data-toggle="tooltip"
													title="Apagar Mensagem"></i> </a>
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

