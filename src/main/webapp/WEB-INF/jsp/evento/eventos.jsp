<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="content-wrapper">
	<section class="content-header">

	<div class="box-header">
		<a href='<spring:url value="/evento-register.html"></spring:url>'>
			<button class="btn btn-success">Novo Evento</button>
		</a>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div>

					<div class="box-header">
						<h3 class="box-title">Eventos</h3>
					</div>

					<div class="box-body">
						<table id="example1" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>Participante</th>
									<th>evento</th>
									<th>Descrição</th>
									<th>Data</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${eventos}" var="evento">
									<tr
										${evento.status.equals('NOVO') ? 'style="font-weight: bold;"' : '' }>
										<td>${evento.participante.nome }</td>
										<td>${evento.nome}</td>
										<td>${evento.descricao }</td>
										<td><fmt:formatDate value="${evento.dtEvento }"
												pattern="dd/MM/yyyy" /></td>
										<td><a
											href='<spring:url value="/evento-detail/${evento.id}.html"></spring:url>'
											data-toggle="tooltip"
											title="Gerenciar evento, visualizar detalhes.."><span
												class="glyphicon glyphicon-list"></span></a></td>
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