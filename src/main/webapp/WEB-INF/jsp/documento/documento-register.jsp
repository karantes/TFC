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
						<h3 class="box-title">Cadastro de Documentos</h3>
					</div>

					<div class="box-body">
						<div class="box-footer">
							<a href='<spring:url value="/documentos.html"></spring:url>'>
								<button class="btn btn-success">Voltar</button>
							</a>
						</div>
						<form:form action="/documento-register.html" method="POST"
							modelAttribute="Documento">
							<div class="col-xs-12">

								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<label>Descrição do Documento:</label><br>
									<form:textarea path="descricao" type="text" id="descricao"
										rows="5" cols="50" name="descricao" />
								</div>

								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<label>URL:</label> <br>
									<form:input path="url" type="text" id="url" name="url"></form:input>
								</div>

								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<label>Tipo:</label> <br>
									<form:select class="form-control" path="tipo" id="tipo"
										name="tipo" multiple="multiple" required="required">
										<form:option value="Administrativo" />Administrativo
										<form:option value="Informativo" />Informativo
										<form:option value="Revisao" />Revisão
									</form:select>
								</div>
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