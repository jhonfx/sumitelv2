<!DOCTYPE html>
<%
response.setHeader("Cache-Control","no-cache"); // Fuerza al cache a obtener una nueva copia de la pagina desde el servidor de origen
response.setHeader("Cache-Control","no-store"); // Indica al cache no guardar la pagina, bajo ninguna circunstancia
response.setDateHeader("Expires", 0);           // Causes the proxy cache to see the page as "stale"
response.setDateHeader('max-age', 0)
response.setIntHeader('Expires', -1)            //prevents caching at the proxy server
response.setHeader("Pragma", "no-cache");       // HTTP 1.0 compatibilidad
%>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <script type="text/javascript" src="${resource(dir: 'javascripts', file: 'jquery-1.8.3.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'javascripts', file: 'underscore.js')}"></script>
    <script type="text/javascript" src="${resource(dir: 'javascripts', file: 'sidebar.js')}"></script>

    <link rel="stylesheet" href="${resource(dir: 'css', file: 'jsgrid.min.css')}"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'jsgrid-theme.min.css')}"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'nav_push.css')}"/>

    <script>
      function openNav() {
          document.getElementById("mySidenav").style.width = "250px";
          document.getElementById("main").style.marginLeft = "250px";
      }

      function closeNav() {
          document.getElementById("mySidenav").style.width = "0";
          document.getElementById("main").style.marginLeft= "0";
      }
    </script>
    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>
<body>

  <div id="mySidenav" class="sidenav">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
    <a href="#">About</a>
    <a href="#">Services</a>
    <a href="#">Clients</a>
    <a href="#">Contact</a>
    <a href="${createLink(controller: 'articulo', action: 'create')}">Articulos</a>
  </div>

  <div id="main">
    <span style="font-size:30px;cursor:pointer" onclick="openNav()">☰ Menu</span>
    <g:layoutBody/>
    <div class="footer" role="contentinfo">SUMITEL S.A de C.V</div>
  </div>

  <!-- <nav class="navbar navbar-inverse">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="#">SUMITEL</a>
      </div>
      <ul class="nav navbar-nav">
        <li class="active"><a href="/">INICIO</a></li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">MODULOS
          <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="${createLink(controller: 'articulo', action: 'create')}">Articulos</a></li>
            <li><a href="${createLink(controller: 'inventario', action: 'create')}">Inventario</a></li>
            <li><a href="${createLink(controller: 'ordenCompra', action: 'create')}">Orden Compra</a></li>
            <li><a href="${createLink(controller: 'proveedor', action: 'create')}">Proveedor</a></li>
            <li><a href="${createLink(controller: 'articulo', action: 'altamasiva')}">Alta Masiva</a></li>
            <li><a href="${createLink(controller: 'articulo', action: 'verarticulos')}">Lista Articulos</a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">USUARIOS
          <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="${createLink(controller: 'usuarios', action: 'create')}">Alta Usuarios</a></li>
            <li><a href="${createLink(controller: 'rol', action: 'create')}">Configurar Rol</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </nav> -->
    

    

    

    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>

      <script type="text/javascript" src="${resource(dir: 'javascripts', file: 'jsgrid.core.js')}"></script>  
      <script type="text/javascript" src="${resource(dir: 'javascripts', file: 'jsgrid.load-indicator.js')}"></script>
      <script type="text/javascript" src="${resource(dir: 'javascripts', file: 'jsgrid.load-strategies.js')}"></script>
      <script type="text/javascript" src="${resource(dir: 'javascripts', file: 'jsgrid.sort-strategies.js')}"></script>
      <script type="text/javascript" src="${resource(dir: 'javascripts', file: 'jsgrid.field.js')}"></script>

      <script type="text/javascript" src="${resource(dir: 'javascripts', file: '/fields/jsgrid.field.text.js')}"></script>
      <script type="text/javascript" src="${resource(dir: 'javascripts', file: '/fields/jsgrid.field.number.js')}"></script>
      <script type="text/javascript" src="${resource(dir: 'javascripts', file: '/fields/jsgrid.field.select.js')}"></script>
      <script type="text/javascript" src="${resource(dir: 'javascripts', file: '/fields/jsgrid.field.checkbox.js')}"></script>
      <script type="text/javascript" src="${resource(dir: 'javascripts', file: '/fields/jsgrid.field.control.js')}"></script>

    <asset:javascript src="application.js"/>

</body>
</html>
