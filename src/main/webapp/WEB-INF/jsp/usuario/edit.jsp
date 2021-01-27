<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/resources/jsp/head.jsp" %>

	<title>Cadastro de Usuários</title> 
</head>

<body class="text-center">

	<form action="/chamados/usuario/save" method="post" class="form-default">
		
		<%@ include file="/resources/jsp/menu.jsp" %>
		
		<h2 class="h3 mb-3 font-weight-normal">Cadastro de Usuários</h2>
		
		<input type="hidden" name="usuario.idUsuario" value="${ usuario.idUsuario }" />
		 
		<%@ include file="/resources/jsp/messages.jsp" %>
		
		<label for="nmUsuario"  class="col-form-label-sm"><fmt:message key="nmUsuario"/></label>
	    <input  id="nmUsuario"  type="text"  class="form-control form-control-sm"  name="usuario.nmUsuario"   value="${ usuario.nmUsuario }" />
		
		<label for="dsEmail"    class="col-form-label-sm"><fmt:message key="dsEmail"/></label>
	    <input  id="dsEmail"    type="email" class="form-control form-control-sm"   name="usuario.dsEmail"    value="${ usuario.dsEmail }"   />
		
		<label for="nrTelefone" class="col-form-label-sm"><fmt:message key="nrTelefone"/></label>
	    <input  id="nrTelefone" type="tel" 	 class="form-control form-control-sm" 	 name="usuario.nrTelefone" value="${ usuario.nrTelefone }" />
	
		<label for="setor" 		class="col-form-label-sm"><fmt:message key="setor"/></label>
		<select id="setor" class="form-control form-control-sm"	name="usuario.setor.idSetor" >
			<option value=0>SELECIONE</option>
			<c:forEach items="${setores}" var="setor">
				<option value="${setor.idSetor}" 
					<c:if test="${ setor.idSetor == usuario.setor.idSetor}">Selected</c:if> >
					${setor.nmSetor}
				</option>		
			</c:forEach>
		</select> 
		
		<label for="dsSenha" 	class="col-form-label-sm"><fmt:message key="dsSenha"/></label>
	    <input  id="dsSenha"  	type="password" class="form-control form-control-sm" 	 name="usuario.dsSenha" />
		
		<label for="dsSenhaConfirm" 	class="col-form-label-sm"><fmt:message key="dsSenhaConfirm"/></label>
	    <input  id="dsSenhaConfirm"  	type="password" class="form-control form-control-sm" 	 name="usuario.dsSenhaConfirm" />
		
		<label for="stUsuario" 	class="col-form-label-sm"><fmt:message key="stUsuario"/></label>
		<select id="stUsuario" name="usuario.stUsuario" class="form-control form-control-sm">
			<c:forEach items="${situacoes}" var="sit">
				<option value="${sit}" <c:if test="${ sit.value == usuario.stUsuario.value}">Selected</c:if>>
					${sit}
				</option>	
			</c:forEach>
		</select> 
		
		<label for="tpUsuario" 	class="col-form-label-sm"><fmt:message key="tpUsuario"/></label>
		<select id="tpUsuario" name="usuario.tpUsuario" class="form-control form-control-sm">
			<c:forEach items="${tipos}" var="tipo">
				<option value="${tipo}" <c:if test="${ tipo.value == usuario.tpUsuario.value}">Selected</c:if>>
					${tipo}
				</option>	
			</c:forEach>
		</select>
		<br>
		
		<button class="btn btn-primary btn-sm" type="submit">Gravar</button>
		<button class="btn btn-primary btn-sm" onclick="forms[0].action='/chamados/usuario/clear'">Limpar</button>
		<c:if test="${ not empty usuario.idUsuario }">
			<button class="btn btn-primary btn-sm" onclick="forms[0].action='/chamados/usuario/delete'">Excluir</button>
		</c:if>
		<button class="btn btn-primary btn-sm" onclick="forms[0].action='/chamados/usuario/list'">Voltar</button>
	</form>
	
	<%@ include file="/resources/jsp/footer.jsp" %>
</body>
</html>
