<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/resources/jsp/head.jsp" %>
	
	<title>Lista de Chamados</title>
</head>

<body class="text-center">

<form action="/chamados/chamado/edit" method="post" class="form-default">

	<%@ include file="/resources/jsp/menu.jsp" %>
	
	<h2 class="h3 mb-3 font-weight-normal">Lista de Chamados</h2>
	
	<button class="btn btn-primary btn-sm" onclick="forms[0].action='/chamados/chamado/edit'">Novo Chamado</button>
	<br><br>
	
	<div style="display:flex; justify-content: center; align-items: center; ">
		
		<div id="div_displaytagTable">
		
			<display:table name="chamados" requestURI="/chamados/chamado/list" sort="page" export="true">
				<display:caption media="pdf">Lista de Chamados</display:caption>
		
				<display:setProperty name="export.xml" value="false"/>
				<display:setProperty name="export.csv" value="false"/>
				<display:setProperty name="export.pdf" value="true"/>
				
				
				<display:column title="editar" href="/chamados/acompanhamento/read" paramId="chamado.idChamado" paramProperty="idChamado" media="html">
					editar
				</display:column>
				
				<display:column property="usuarioSolicitante.nmUsuario" titleKey="nmUsuario" sortable="true"/>
				<display:column property="dsChamado" titleKey="dsChamado" sortable="true"/>
				<display:column property="stChamado" titleKey="stChamado" sortable="true"/>
			</display:table>
		</div>
	</div>
</form>
</body>
<%@ include file="/resources/jsp/footer.jsp" %>
</html>
