<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <c:url value="/resources/" /> -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<!-- %@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>  -->

<aside class="main-sidebar">

	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">

		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header">Menu</li>
			<li class="treeview"><a href="#"> <i
					class="fa fa-newspaper-o"></i> <span>Projetos</span> <i
					class="fa fa-angle-left pull-right"></i>
			</a>
				<ul class="treeview-menu">
					<li><a href='<spring:url value="/projetos.html"></spring:url>'>
							<i class="fa fa-circle-o"></i>Projetos
					</a></li>
					<security:authorize access="hasAuthority('1')">
						<li><a
							href='<spring:url value="/semestres.html"></spring:url>'> <i
								class="fa fa-circle-o"></i>Semestres
						</a></li>
					</security:authorize>
				</ul></li>
			<security:authorize access="hasAuthority('1')">
				<li class="treeview"><a href="#"> <i class="fa fa-users"></i>
						<span>Usuarios</span> <i class="fa fa-angle-left pull-right"></i>
				</a>
					<ul class="treeview-menu">
						<li><a
							href='<spring:url value="/usuarios.html"></spring:url>'> <i
								class="fa fa-circle-o"></i>Usuarios
						</a></li>
					</ul></li>
			</security:authorize>
			<li class="treeview"><a href="#"> <i
					class="fa fa-check-square-o"></i> <span>Eventos</span> <i
					class="fa fa-angle-left pull-right"></i>
			</a>
				<ul class="treeview-menu">
					<li><a href='<spring:url value="/eventos.html"></spring:url>'>
							<i class="fa fa-circle-o"></i>Eventos
					</a></li>
				</ul></li>

			<li class="treeview"><a href="#"> <i
					class="fa fa-envelope-o"></i> <span>Mensagens</span> <i
					class="fa fa-angle-left pull-right"></i>
			</a>
				<ul class="treeview-menu">
					<li><a
						href='<spring:url value="/mensagem-register.html"></spring:url>'>
							<i class="fa fa-circle-o"></i>Nova Mensagem
					</a></li>

					<li><a
						href='<spring:url value="/mensagens-enviadas.html"></spring:url>'>
							<i class="fa fa-circle-o"></i>Enviadas
					</a></li>

					<li><a
						href='<spring:url value="/mensagens-recebidas.html"></spring:url>'>
							<i class="fa fa-circle-o"></i>Recebidas
					</a></li>
				</ul></li>

			<li class="treeview"><a href="#"> <i
					class="fa fa-file-archive-o"></i> <span>Documentos</span> <i
					class="fa fa-angle-left pull-right"></i>
			</a>
				<ul class="treeview-menu">
					<li><a
						href='<spring:url value="/documentos.html"></spring:url>'> <i
							class="fa fa-circle-o"></i>Documentos
					</a></li>

					<li><a
						href='<spring:url value="/documento-register.html"></spring:url>'>
							<i class="fa fa-circle-o"></i>Enviar Novo Documento
					</a></li>
				</ul></li>

			<li class="treeview"><a href="#"> <i class="fa fa-calendar"></i>
					<span>Frequencias</span> <i class="fa fa-angle-left pull-right"></i>
			</a>
				<ul class="treeview-menu">
					<li><a
						href='<spring:url value="/frequencias.html"></spring:url>'> <i
							class="fa fa-circle-o"></i>Frequencias
					</a></li>
					<security:authorize access="hasAuthority('2')">
						<li><a
							href='<spring:url value="/frequencia-register.html"></spring:url>'>
								<i class="fa fa-circle-o"></i>Nova Frequencia
						</a></li>
					</security:authorize>
				</ul></li>
			<li><a
				href='<spring:url value="/download-apk.html"></spring:url>'> <i
					class="fa fa-download"></i>Download APK
			</a></li>
		</ul>
		<!-- /.sidebar menu -->
	</section>
	<!-- /.sidebar -->
</aside>