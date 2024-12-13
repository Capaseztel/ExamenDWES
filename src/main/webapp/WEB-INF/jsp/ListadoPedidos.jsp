<%@ page import="java.sql.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.examendwes.modelo.cliente" %>
<%@ page import="com.example.examendwes.modelo.comercial" %>
<%@ page import="com.example.examendwes.modelo.pedido" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Listado de pedidos</title>
  <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
  <link rel="stylesheet" type="text/css" href="estilos.css" />
  <link rel="stylesheet" type="text/css" href="css/backtop.css" />
</head>
<body class="bg-light">

<!-- BackToTop Button -->
<a href="javascript:void(0);" id="backToTop" class="back-to-top">
  <i class="arrow"></i><i class="arrow"></i>
</a>

<div class="container bg-white sticky-top">
  <div class="row mb-2 border-bottom">
    <div class="col-md-8 h1">Resumen clientes x comercial</div>
  </div>
  <div class="row">
    <div class="col-md-5 h3">Comercial</div>
    <div class="col-md-5 h3">#Clientes</div>
  </div>
</div>

<div class="container bg-light">
    <%
    List<pedido> listadoPedido = (List<pedido>) request.getAttribute("listadoPedido");
    List<cliente> listadoCliente = (List<cliente>) request.getAttribute("listadoCliente");
    List<comercial> listadoComercial = (List<comercial>) request.getAttribute("listadoComercial");
    for (comercial comercial: listadoComercial) {
  %>
  <div id="<%=comercial.getId()%>" class="row mt-2 body">
    <div class="col-md-5 align-self-center"><%=comercial.getNombre() + " " + comercial.getApellido1() + " " + comercial.getApellido2()%>
    </div>
    <div class="col-md-5 align-self-center"><%
      int i = 0;
      for (pedido pedido: listadoPedido) {
        if (pedido.getid_comercial() == comercial.getId()) {
          i++;
        }
      }
      out.println(i);
    %>
    </div>
  </div>
    <%

    }
  %>

      <div>
        <h1>Buscador</h1>
        <form method="get" action="ListarPedidosServlet">
          <input type="number" placeholder="Cantidad minima" name="cantidadBuscarMin">
          <input type="number" placeholder="Cantidad maxima" name="cantidadBuscarMax">
          <input type="submit">
        </form>
      </div>

<div class="container bg-white sticky-top">
  <div class="row mb-2 border-bottom">
    <div class="col-md-8 h1">Listado de Pedidos</div>
    <div class="col-md-4 align-self-center" ><form method="get" action="CrearPedidoServlet">
      <input class="btn btn-primary"  type="submit" value="Crear pedido">
    </form></div>
  </div>
  <div class="row">
    <div class="col-md-1 h3">ID</div>
    <div class="col-md-4 h3">Cantidad</div>
    <div class="col-md-1 h3">Fecha</div>
    <div class="col-md-1 h3">Cliente</div>
    <div class="col-md-1 h3">Comercial</div>
    <div class="col-md-3 h3 text-center">Operación</div>
  </div>
</div>

<div class="container bg-light">
  <%
    if (request.getParameter("cantidadBuscarMin") != null && request.getParameter("cantidadBuscarMax") != null) {
      double cantidadBuscarMin = Double.parseDouble(request.getParameter("cantidadBuscarMin"));
      double cantidadBuscarMax = Double.parseDouble(request.getParameter("cantidadBuscarMax"));
      listadoPedido = listadoPedido.stream().filter(p -> p.getTotal() > cantidadBuscarMin && p.getTotal() < cantidadBuscarMax).toList();
    }

    for (pedido pedido: listadoPedido) {
  %>
  <div id="<%=pedido.getId()%>" class="row mt-2 body">
    <div class="col-md-1 align-self-center"><%=pedido.getId() %>
    </div>
    <div class="col-md-4 align-self-center"><%= pedido.getTotal()%>
    </div>
    <div class="col-md-1 align-self-center"><%=pedido.getfecha() %>
    </div>
    <div class="col-md-1 align-self-center"><%
      for (cliente cliente : listadoCliente) {
        if (cliente.getId() == pedido.getid_cliente()) {
          out.println(cliente.getNombre() + ", " + cliente.getApellido1() + " " + cliente.getApellido2() );
        }
      }
    %>
    </div>
      <div class="col-md-1 align-self-center"><%
        for (comercial comercial : listadoComercial) {
          if (comercial.getId() == pedido.getid_cliente()) {
            out.println(comercial.getNombre() + ", " + comercial.getApellido1() + " " + comercial.getApellido2() );
          }
        }
      %>
    </div>


    <div class="col-md-3 align-self-center text-center">
      <form class="d-inline" method="post" action="#">
        <input type="hidden" name="codigo" value="<%=pedido.getId() %>"/>
        <input class="btn btn-primary"  type="submit" value="Borrar">
      </form>
      <form class="d-inline" method="get" action="#">
        <input type="hidden" name="codigo" value="<%=pedido.getId() %>"/>
        <input class="btn btn-primary"  type="submit" value="Editar">
      </form>
    </div>
  </div>
  <%
      //v--- FIN DEL BUCLE FOR CON HTML INCRUSTADO
    } // for
  %>
</div>

<script type="text/javascript" src="js/jquery.js" ></script>
<script type="text/javascript">
  $(function (){
    //IMPLEMENTANDO UN BOTÓN backToTop
    let btn = $('#backToTop');
    $(window).on('scroll', function() {
      if ($(window).scrollTop() > 300) {
        btn.addClass('show');
      } else {
        btn.removeClass('show');
      }
    });
    btn.on('click', function(e) {
      e.preventDefault();
      $('html, body').animate({
        scrollTop: 0
      }, '300');
    });
  });

</script>
<%
  Integer newclienteID = (Integer) request.getAttribute("newclienteID");
  if (newclienteID != null) {
%>

<div class="modal fade" id="newclienteIDModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Grabar cliente</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        Grabado correctamente cliente con ID <%=newclienteID%>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary close" data-dismiss="modal">Cerrar</button>

      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
  //DINAMISMO CON JQUERY..
  //CUANDO SE CARGA EL DOM JQUERY EJECUTA SOBRE SELECTOR DE CAPA MODAL AL MODAL
  $(function (){
    $('#newclienteIDModal').modal('show');
    $('#newclienteIDModal').on('click', 'button.close', function (eventObject) {
      $('#newclienteIDModal').modal('hide');

      //PARA HACER SMOOTH SCROLL AL ELEMENTO NUEVO ELEMENTO EN LA PÁGINA
      $('html, body').animate({
        scrollTop: $('#<%=newclienteID%>').offset().top
      }, 2000, () => $('#<%=newclienteID%>').addClass('highlight'));
    });
  });
</script>
<% } %>
<script type="text/javascript" src="js/bootstrap.bundle.js" ></script>
</body>
</html>
