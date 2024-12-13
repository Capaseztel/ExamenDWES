<%@ page import="java.util.List" %>
<%@ page import="com.example.examendwes.modelo.cliente" %>
<%@ page import="com.example.examendwes.modelo.comercial" %>
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
  List<cliente> listaCliente = (List<cliente>) request.getAttribute("listaCliente");
  List<comercial> listaComercial = (List<comercial>) request.getAttribute("listaComercial");

%>
<div class="container bg-light">
  <form method="post" action="CrearPedidoServlet">
    <div class="row body mt-2">
      <div class="col-md-6 align-self-center">total</div>
      <div class="col-md-6 align-self-center"><input type="number" name="total"/></div>
    </div>
    <div class="row body mt-2">
      <div class="col-md-6 align-self-center">Fecha</div>
      <div class="col-md-6 align-self-center"><input type="text" name="fecha"/></div>
    </div>
    <div class="row body mt-2">
      <div class="col-md-6 align-self-center">cliente</div>
      <div class="col-md-6 align-self-center">
        <select name="cliente">
          <%for (cliente cliente : listaCliente) {
            out.println("<option value=\"" + cliente.getId() + "\">" + (cliente.getNombre() + " " + cliente.getApellido1() + " " + cliente.getApellido2()) + "</option>");
          }
          %>
        </select>
      </div>
    </div>
    <div class="row body mt-2">
      <div class="col-md-6 align-self-center">comercial</div>
      <div class="col-md-6 align-self-center">
        <select name="comercial">
          <%for (comercial comercial : listaComercial) {
            out.println("<option value=\"" + comercial.getId() + "\">" + (comercial.getNombre() + " " + comercial.getApellido1() + " " + comercial.getApellido2()) + "</option>");
          }
          %>
        </select>
      </div>
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