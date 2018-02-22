<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script>
	$(function() {
		//Initialize Select2 Elements
		$('.select2').select2()
	})
</script>

<div class="content-wrapper">
	<section class="content-header">
	<div class="row">
		<div class="col-xs-12">
			<div class="box">
				<div>

					<div class="box-header">
						<h3 class="box-title">Cadastro de Projetos</h3>
					</div>

					<div class="box-body">
						<div class="box-footer">
							<a href='<spring:url value="/projetos.html"></spring:url>'>
								<button class="btn btn-success">Voltar</button>
							</a>
						</div>
						<form:form action="/projeto-register.html" method="POST"
							modelAttribute="Projeto">
							<div class="col-xs-12">
								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<label>Nome do Projeto:</label> <br>
									<form:input path="nome" type="text" id="nome" name="nome"
										required="required"></form:input>
								</div>

								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<label>Descrição do Projeto:</label>
									<form:textarea path="descricao" class="form-control" rows="5"
										cols="50" id="descricao" name="descricao" required="required"></form:textarea>
								</div>

								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<label><form:checkbox path="ativo" id="ativo"
											name="ativo" value="true" checked="checked" />Ativo</label>
								</div>

								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<label>Semestre:</label> <br> <select
										class="form-control select2" id="idSemestre" name="idSemestre"
										required>
										<option value=""></option>
										<c:forEach items="${semestres }" var="semestre">
											<option value="${semestre.id }">${semestre.ano }-
												${semestre.gotSemestre() }</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<label>Alunos:</label> <br> <select
										class="form-control select2" id="idAlunos" name="idAlunos"
										multiple="multiple" required>
										<c:forEach items="${alunos }" var="aluno">
											<option value="${aluno.id }">${aluno.nome }</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group col-xs-7 col-sm-6 col-lg-8">
									<label>Orientadores:</label> <br> <select
										class="form-control select2" id="idOrientadores"
										name="idOrientadores" multiple="multiple" required>
										<c:forEach items="${orientadores }" var="orientador">
											<option value="${orientador.id }">${orientador.nome }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="box-footer">
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