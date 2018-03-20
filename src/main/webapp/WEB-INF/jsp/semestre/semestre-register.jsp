<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="content-wrapper">
	<section class="content-header">
	<div class="box-header">
		<a href='<spring:url value="/semestres.html"></spring:url>'>
			<button class="btn btn-success">Voltar</button>
		</a>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div>

					<div class="box-header with-border">
						<h3 class="box-title">Cadastro de Semestres</h3>
					</div>

					<div class="box-body">
						<form:form action="/semestre-register.html" method="POST"
							modelAttribute="Semestre">
							<div class="col-xs-12">
								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<label>Ano:</label> <br>
									<form:select path="ano" class="form-control" id="ano"
										name="ano">
										<form:option value="2017">2017</form:option>
										<form:option value="2018">2018</form:option>
										<form:option value="2019">2019</form:option>
										<form:option value="2020">2020</form:option>
										<form:option value="2021">2021</form:option>
									</form:select>
								</div>

								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<label>Semestre:</label> <br>
									<form:select path="semestre" class="form-control" id="semestre"
										name="semestre">
										<form:option value="1">Primeiro</form:option>
										<form:option value="2">Segundo</form:option>
									</form:select>
								</div>

								<div class="box-footer col-xs-12">
									<button type="submit" class="btn btn-success">Cadastrar</button>
								</div>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>
</div>