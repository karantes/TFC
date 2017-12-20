<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="content-wrapper">
	<section class="content-header">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div>

					<div class="box-header">
						<h3 class="box-title">Semestres</h3>
					</div>

					<div class="box-body">
						<div class="box-footer">
							<a
								href='<spring:url value="/semestre-register.html"></spring:url>'>
								<button class="btn btn-success">Novo Semestre</button>
							</a>
						</div>

						<table id="example1" class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>Ano</th>
									<th>Semestre</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${semestres}" var="semestre">
									<tr>
										<td>${semestre.ano}</td>
										<c:choose>
											<c:when test="${semestre.semestre == 1}">
												<td>Primeiro</td>
											</c:when>
											<c:when test="${semestre.semestre == 2}">
												<td>Segundo</td>
											</c:when>
										</c:choose>
										<td><a
											href='<spring:url value="/semestre-detail/${semestre.id}.html"></spring:url>'
											data-toggle="tooltip" title="visualizar Semestre.."><span
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