<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <nav class="navbar navbar-light bg-light">
  
  <a class="navbar-brand" href="/chamados/">
    <img src="/chamados/resources/img/Ghost.png" width="30" height="30" class="d-inline-block align-top" alt="">
    JSM
    </a>
  </nav>
  
  <!-- BOTÃO HAMBURGUER -->
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#conteudoNavbarSuportado" aria-controls="conteudoNavbarSuportado" aria-expanded="false" aria-label="Alterna navegação">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="conteudoNavbarSuportado">
    <ul class="navbar-nav mr-auto">
      
      
      <li class="nav-item ">
        <a class="nav-link" href="/chamados/chamado/list">Chamados</a>
      </li>
      
      
      <li class="nav-item">
        <a class="nav-link" href="/chamados/meucadastro">Meu cadastro</a>
      </li>


      <!-- SOMENTE PARA OS ADMINISTRADORES  -->
      
      <c:if test="${ auxSession.usuario.tpUsuario eq 'ADMIN' }">
      		 <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          Configurações
		        </a>
		        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
		          <a class="dropdown-item" href="/chamados/usuario/list">Usuários</a>
		          <a class="dropdown-item" href="/chamados/setor/list">Setores</a>
		        </div>
		     </li>
      </c:if>
      
      <li class="nav-item">
        <a class="nav-link" href="/chamados/login/logout">Sair</a>
      </li>
      
    </ul>
  </div>
</nav>

