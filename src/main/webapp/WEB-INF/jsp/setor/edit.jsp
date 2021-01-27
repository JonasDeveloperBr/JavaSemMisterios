<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/resources/jsp/head.jsp" %>
	
	<title>Cadastro de Setores</title>
</head>

<body class="text-center">

	<form action="/chamados/setor/save" method="post" class="form-default">
		<%@ include file="/resources/jsp/menu.jsp" %>
		
		<h2 class="h3 mb-3 font-weight-normal">Cadastro de Setores</h2>
		
		<input type="hidden" name="setor.idSetor" value="${ setor.idSetor }" />
		
		<%@ include file="/resources/jsp/messages.jsp" %>
		
		<label for="nmSetor"  class="col-form-label-sm"><fmt:message key="nmSetor"/></label>
	    <input  id="nmSetor"  type="text"  class="form-control form-control-sm"  name="setor.nmSetor"   value="${ setor.nmSetor }" />
		
		<br>
		<!-- BOTÕES -->
		<button class="btn btn-primary btn-sm" type="submit">Gravar</button>
		<button class="btn btn-primary btn-sm" onclick="forms[0].action='/chamados/setor/clear'">Limpar</button>
		<c:if test="${ not empty setor.idSetor }">
			<button class="btn btn-primary btn-sm" onclick="forms[0].action='/chamados/setor/delete'">Excluir</button>
		</c:if>
		<button class="btn btn-primary btn-sm" onclick="forms[0].action='/chamados/setor/list'">Voltar</button>
	</form>

<%@ include file="/resources/jsp/footer.jsp" %>
</body>

</html>