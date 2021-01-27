<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!--  MENSAGEM DE SUCESSO -->	
<c:if test="${not empty vmessages.success.from('success') }">
	<div class="alert alert-success alert-dismissible fade show" data-dismiss="alert">
        <fmt:message key="${vmessages.success.from('success')}"/>
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
     	</div>
    	</c:if>
    
<!-- MENSAGENS DE ERRO -->
<c:if test="${ not empty errors}">
	<div class="alert alert-danger alert-dismissible fade show" data-dismiss="alert">
		<c:forEach items="${ errors }" var="error">
			<strong><fmt:message key="${ error.category }"/></strong> - ${ error.message }<br>
		</c:forEach>
		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
         	<span aria-hidden="true">&times;</span>
       	</button>		
	</div>
</c:if>
