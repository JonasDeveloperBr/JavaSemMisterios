<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/resources/jsp/head.jsp" %>
	
	<title>Chamado - Acompanhamento</title>
</head>

<body class="text-center">

	<form action="/chamados/acompanhamento/save" method="post" class="form-default">
		<%@ include file="/resources/jsp/menu.jsp" %>
		
		<h2 class="h3 mb-3 font-weight-normal">Cadastro de Acompanhamentos</h2>
		
		<%@ include file="/resources/jsp/messages.jsp" %>
		
		<label for="idChamado" 		class="col-form-label-sm"><fmt:message key="idChamado"/></label>
		<input  id="idChamado"      class="form-control form-control-sm" type="text" name="acompanhamento.chamado.idChamado" value="${chamado.idChamado}" readonly/>
		
		<label for="stAcompanhamento" 	class="col-form-label-sm"><fmt:message key="stAcompanhamento"/></label>
		
		<c:if test="${ chamado.stChamado ne 'ENCERRADO' }">
			<select name="acompanhamento.stAcompanhamento" class="form-control form-control-sm">
				<c:forEach items="${situacoes}" var="sit">
					<option value="${sit}">
						${sit}
					</option>	
				</c:forEach>
			</select> 
		</c:if>
		<c:if test="${ chamado.stChamado eq 'ENCERRADO' }">
			<input  id="idSetor" class="form-control form-control-sm" type="text" name="chamado.stChamado" value="${chamado.stChamado}" readonly/>
		</c:if>
		
		<label for="idSetor" class="col-form-label-sm"><fmt:message key="idSetor"/></label>
		<input  id="idSetor" class="form-control form-control-sm" type="text" name="acompanhamento.chamado.setorDestino.nmSetor" value="${chamado.setorDestino.nmSetor}" readonly/>
		
		<label for="idUsuario" class="col-form-label-sm"><fmt:message key="idSetor"/></label>
		<input  id="idUsuario" class="form-control form-control-sm" type="text" name="acompanhamento.chamado.usuarioSolicitante.nmUsuario" value="${chamado.usuarioSolicitante.nmUsuario}" readonly/>
		
		<label for="dsChamado"  class="col-form-label-sm"><fmt:message key="dsChamadoSolicitacao"/></label>
		<textarea id="dsChamado" rows="2" cols="50" class="form-control form-control-sm"  name="chamado.dsChamado" readonly="readonly">${ chamado.dsChamado }</textarea>

		<c:if test="${ chamado.stChamado ne 'ENCERRADO' }">
		<label for="dsAcompanhamento"  class="col-form-label-sm"><fmt:message key="dsAcompanhamento"/></label>
		<textarea id="dsAcompanhamento" rows="4" cols="50" class="form-control form-control-sm"  name="acompanhamento.dsAcompanhamento" >${ acompanhamento.dsAcompanhamento }</textarea>		
		</c:if>

		<br>
		<!-- BOTÕES -->
		<c:if test="${ chamado.stChamado ne 'ENCERRADO' }">
			<button class="btn btn-primary btn-sm" type="submit">Gravar</button>
			<button class="btn btn-primary btn-sm" onclick="forms[0].action='/chamados/acompanhamento/clear'">Limpar</button>
		</c:if>
		
		<button class="btn btn-primary btn-sm" onclick="forms[0].action='/chamados/chamado/list'">Voltar</button>
		<br><br>
		
		<!-- LISTA DE ACOMPANHAMENTOS -->
		<div style="display:flex; justify-content: center; align-items: center; ">
			
			<div id="div_displaytagTable">
			
				<display:table name="chamado.acompanhamentos">
					<display:column property="dtAcompanhamento" titleKey="dtAcompanhamento" format="{0,date,dd/MM/yyyy HH:mm:ss}" />
					<display:column property="usuario.nmUsuario" titleKey="nmUsuario" />
					<display:column property="dsAcompanhamento" titleKey="dsAcompanhamento" />
					<display:column property="stAcompanhamento" titleKey="stAcompanhamento" />
				</display:table>
			</div>
		</div>
	</form>

<%@ include file="/resources/jsp/footer.jsp" %>
</body>

</html>