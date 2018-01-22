<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <c:url value="/resources/" /> -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<!-- %@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>  -->

<script>
	var tags = [ "c++", "java", "php", "coldfusion", "javascript", "asp",
			"ruby" ];
	$("#autocomplete")
			.autocomplete(
					{
						source : function(request, response) {
							var matcher = new RegExp("^"
									+ $.ui.autocomplete
											.escapeRegex(request.term), "i");
							response($.grep(tags, function(item) {
								return matcher.test(item);
							}));
						}
					});
</script>

<aside class="main-sidebar">

	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel (optional) -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="<c:url value="" />" class="img-circle" alt="User Image">
			</div>

			<div class="pull-left info">
				<p>Kaique Arantes</p>
				<!-- Status -->
				<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
			</div>
		</div>
		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header">Admin</li>

			<li class="treeview"><a href="#"> <i
					class="fa fa-newspaper-o"></i> <span>Projeto</span> <i
					class="fa fa-angle-left pull-right"></i>
			</a>
				<ul class="treeview-menu">
					<li><a href='<spring:url value="/projetos.html"></spring:url>'>
							<i class="fa fa-circle-o"></i>Projetos
					</a></li>
					<li><a
						href='<spring:url value="/semestres.html"></spring:url>'> <i
							class="fa fa-circle-o"></i>Semestres
					</a></li>
				</ul></li>
			<li class="treeview"><a href="#"> <i class="fa fa-users"></i>
					<span>Usuarios</span> <i class="fa fa-angle-left pull-right"></i>
			</a>
				<ul class="treeview-menu">
					<li><a href='<spring:url value="/usuarios.html"></spring:url>'>
							<i class="fa fa-circle-o"></i>Usuarios
					</a></li>
				</ul></li>
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
						href='<spring:url value="/mensagens.html"></spring:url>'> <i
							class="fa fa-circle-o"></i>Mensagens
					</a></li>
				</ul></li>
		</ul>
		<!-- /.sidebar menu -->
	</section>
	<!-- /.sidebar -->
</aside>