<!doctype html>
<html lang="pt-br">
  <head>
  	<%@ include file="/resources/jsp/head.jsp" %>
	<link type="text/css" rel="stylesheet" href="/chamados/resources/css/login.css" />
	
    <title>Login - Java Sem Mistérios</title>
  </head>

  <body class="text-center">
    <form class="form-signin" method="post" action="/chamados/login/check">
    
    	<%@ include file="/resources/jsp/messages.jsp" %>
    
      <img class="mb-4" src="/chamados/resources/img/Ghost.png" alt="" width="72" height="72">
      <h1 class="h3 mb-3 font-weight-normal">Java Sem Mistérios</h1>
    
      <label for="nmUser" class="sr-only">Endereço de email</label>
      <input  id="nmUser" type="text"  class="form-control" placeholder="Seu email" required autofocus name="nmUser">
      
      <label for="dsSenha" class="sr-only">Senha</label>
      <input  id="dsSenha" type="password"  class="form-control" placeholder="Senha" required name="dsSenha">
      
      <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
      <p class="mt-5 mb-3 text-muted">&copy; Luiz Lima</p>
    </form>
  </body>
  <%@ include file="/resources/jsp/footer.jsp" %>
</html>
