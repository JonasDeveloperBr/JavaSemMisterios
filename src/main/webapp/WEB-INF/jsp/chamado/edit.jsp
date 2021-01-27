<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/resources/jsp/head.jsp" %>
	
	<title>Cadastro de Chamados</title>
</head>

<body class="text-center">

	<form action="/chamados/chamado/save" method="post" class="form-default">
		<%@ include file="/resources/jsp/menu.jsp" %>
		
		<h2 class="h3 mb-3 font-weight-normal">Cadastro de Chamados</h2>
		
		<input type="hidden" name="chamado.idChamado" value="${ chamado.idChamado }" />
		
		<%@ include file="/resources/jsp/messages.jsp" %>
		
		<label for="setor" 		class="col-form-label-sm"><fmt:message key="setor"/></label>
		<select id="setor" class="form-control form-control-sm"	name="chamado.setorDestino.idSetor" >
			<option value=0>SELECIONE</option>
			<c:forEach items="${setores}" var="setor">
				<option value="${setor.idSetor}" 
					<c:if test="${ setor.idSetor == chamado.setorDestino.idSetor}">Selected</c:if> >
					${setor.nmSetor}
				</option>		
			</c:forEach>
		</select>
		
		<label for="dsChamado"  class="col-form-label-sm"><fmt:message key="dsChamado"/></label>
		<textarea id="dsChamado" rows="4" cols="50" class="form-control form-control-sm"  name="chamado.dsChamado">${ chamado.dsChamado }</textarea>
		
		<label for="stChamado" 	class="col-form-label-sm"><fmt:message key="stChamado"/></label>
		<select name="chamado.stChamado" class="form-control form-control-sm">
			<option value=1>NOVO</option>
		</select> 
		<br>
		<!-- BOTÕES -->
		<button class="btn btn-primary btn-sm" type="submit">Gravar</button>
		<button class="btn btn-primary btn-sm" onclick="forms[0].action='/chamados/chamado/clear'">Limpar</button>
		<c:if test="${ not empty chamado.idChamado }">
			<button class="btn btn-primary btn-sm" onclick="forms[0].action='/chamados/chamado/delete'">Excluir</button>
		</c:if>
		<button class="btn btn-primary btn-sm" onclick="forms[0].action='/chamados/chamado/list'">Voltar</button>
	</form>

<%@ include file="/resources/jsp/footer.jsp" %>
</body>

</html>