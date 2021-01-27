<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/resources/jsp/head.jsp" %>
	
	<title>Lista de Usuários</title> 

</head>

<body class="text-center">

<form action="/chamados/usuario/edit" method="post" class="form-default">

	<%@ include file="/resources/jsp/menu.jsp" %>
	
	<h2 class="h3 mb-3 font-weight-normal">Lista de Usuários</h2>
	
	<button class="btn btn-primary btn-sm" onclick="forms[0].action='/chamados/usuario/edit'">Novo Usuário</button>
	<br><br>
	
	<div style="display:flex; justify-content: center; align-items: center; ">
		<div id="div_displaytagTable">
		
			<display:table name="usuarios" requestURI="/chamados/usuario/list" sort="page" export="true">
				<display:caption media="pdf">Lista de Usuários</display:caption>
		
				<display:setProperty name="export.xml" value="false"/>
				<display:setProperty name="export.csv" value="false"/>
				<display:setProperty name="export.pdf" value="true"/>
				
				
				<display:column title="editar" href="read" paramId="usuario.idUsuario" paramProperty="idUsuario" media="html">
					editar
				</display:column>
				
				<display:column property="nmUsuario" titleKey="nmUsuario" sortable="true"/>
				<display:column property="dsEmail" titleKey="dsEmail" sortable="true"/>
				<display:column property="nrTelefone" titleKey="nrTelefone" sortable="true"/>
				
				<display:column property="stUsuario" titleKey="stUsuario" sortable="true"/>
			</display:table>
		
		</div>
	</div>
</form>

<%@ include file="/resources/jsp/footer.jsp" %>
</body>

</html>
