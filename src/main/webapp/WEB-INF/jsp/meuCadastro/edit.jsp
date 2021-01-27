<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="/resources/jsp/head.jsp" %>

	<title>Meu Cadastro</title> 
</head>

<body class="text-center">

	<form action="/chamados/savemeucadastro" method="post" class="form-default">
		
		<%@ include file="/resources/jsp/menu.jsp" %>
		
		<h2 class="h3 mb-3 font-weight-normal">Meu Cadastro</h2>
		
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
		
		<br>
		
		<button class="btn btn-primary btn-sm" type="submit">Gravar</button>
	</form>
	
	<%@ include file="/resources/jsp/footer.jsp" %>
</body>
</html>
