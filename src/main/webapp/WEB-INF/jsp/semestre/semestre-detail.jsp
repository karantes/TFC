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
					<h3 class="box-title">Semestre</h3>
				</div>
				<div class="box-body">
					<div class="col-xs-7 col-sm-6 col-lg-8">
						<a href='<spring:url value="/semestres.html"></spring:url>'>
							<button class="btn btn-success">Voltar</button>
						</a>
					</div>
					<div class="col-xs-7 col-sm-6 col-lg-8">
						<a
							href='<spring:url value="/semestre-register.html"></spring:url>'>
							<button class="btn btn-success">Novo Semestre</button>
						</a>
					</div>
				</div>
				<div class="box-body">

					<div class="box-footer">
						<div class="col-xs-12">
							<form:form action="/semestre-register.html" method="POST"
								modelAttribute="semestre">
								<div class="col-xs-12">
									<div class="form-group col-xs-7 col-sm-6 col-lg-8">
										<label>Semestre</label> <br>
										<form:input path="ano" type="text" id="ano" name="ano"
											value="${semestre.ano }" disabled="true"></form:input>
									</div>
									<div class="form-group col-xs-7 col-sm-6 col-lg-8">
										<label>Semestre</label> <br>
										<form:input path="semestre" type="text" id="semestre"
											name="semestre" value="${semestre.semestre }" disabled="true"></form:input>
									</div>
								</div>
								<div class="box-footer">
									<button type="submit" class="btn btn-success" disable>Atualizar</button>
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