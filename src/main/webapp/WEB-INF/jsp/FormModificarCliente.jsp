<%@ page import="com.example.examendwes.modelo.cliente" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
  <link rel="stylesheet" type="text/css" href="estilos.css" />
</head>
<body class="bg-light">
<div class="container bg-white">
  <div class="row border-bottom">
    <div class="col-12 h2">Introduzca los datos del nuevo socio</div>
  </div>
</div>
<%
  cliente cliente = (cliente) request.getAttribute("cliente");
%>
<div class="container bg-light">
  <form method="post" action="ModificarClienteServlet">
    <div class="row body mt-2">
      <input type="hidden" name="id" value="<%=cliente.getId()%>">
      <div class="col-md-6 align-self-center">Nombre</div>
      <div class="col-md-6 align-self-center"><input type="text" name="nombre" value="<%=cliente.getNombre()%>"/></div>
    </div>
    <div class="row body mt-2">
      <div class="col-md-6 align-self-center">Apellido1</div>
      <div class="col-md-6 align-self-center"><input type="text" name="apellido1" value="<%=cliente.getApellido1()%>"/></div>
    </div>
    <div class="row body mt-2">
      <div class="col-md-6 align-self-center">Apellido2</div>
      <div class="col-md-6 align-self-center"><input type="text" name="apellido2" value="<%=cliente.getApellido2()%>"/></div>
    </div>
    <div class="row body mt-2">
      <div class="col-md-6 align-self-center">Ciudad</div>
      <div class="col-md-6 align-self-center"><input type="text" name="ciudad" value="<%=cliente.getCiudad()%>"/></div>
    </div>
    <div class="row body mt-2">
      <div class="col-md-6 align-self-center">Categoria</div>
      <div class="col-md-6 align-self-center"><input type="number" name="categoria" value="<%=cliente.getCategoria()%>"/></div>
    </div>
    <div class="row mt-2">
      <div class="col-md-6">
        &nbsp;
      </div>
      <div class="col-md-6 align-self-center">
        <input class="btn btn-primary" type="submit" value="Aceptar">
      </div>
    </div>
  </form>
  <%
    //                                                 v---- RECOGER MENSAJE DE ERROR DEL ÁMBITO request
    String error = (String) request.getAttribute("error");
//            v---- SI ESTÁ PRESENTE INFORMAR DEL ERROR
    if (error != null) {
  %>
  <div class="row mt-2">
    <div class="col-6">
      <div class="alert alert-danger alert-dismissible fade show">
        <strong>Error!</strong> <%=error%>
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
      </div>
    </div>
  </div>
  <%
    }
  %>
</div>
<script src="js/bootstrap.bundle.js" ></script>
</body>
</html>