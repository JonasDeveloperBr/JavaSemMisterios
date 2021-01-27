<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/resources/jsp/head.jsp" %>
	
	<title>Lista de Setores</title>
</head>

<body class="text-center">

<form action="/chamados/setor/edit" method="post" class="form-default">

	<%@ include file="/resources/jsp/menu.jsp" %>
	
	<h2 class="h3 mb-3 font-weight-normal">Lista de Setores</h2>
	
	<button class="btn btn-primary btn-sm" onclick="forms[0].action='/chamados/setor/edit'">Novo Setor</button>
	<br><br>
	
	<div style="display:flex; justify-content: center; align-items: center; ">
		
		<div id="div_displaytagTable">
		
			<display:table name="setores" requestURI="/chamados/setor/list" sort="page" export="true">
				<display:caption media="pdf">Lista de Setores</display:caption>
		
				<display:setProperty name="export.xml" value="false"/>
				<display:setProperty name="export.csv" value="false"/>
				<display:setProperty name="export.pdf" value="true"/>
				
				
				<display:column title="editar" href="read" paramId="setor.idSetor" paramProperty="idSetor" media="html">
					editar
				</display:column>
				
				<display:column property="nmSetor" titleKey="nmSetor" sortable="true" style="width:300px"/>
			</display:table>
		</div>
	</div>
</form>
</body>
<%@ include file="/resources/jsp/footer.jsp" %>
</html>
