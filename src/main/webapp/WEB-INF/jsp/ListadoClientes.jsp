<%@ page import="java.sql.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.examendwes.modelo.cliente" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Listado de clientes</title>
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
    <div class="col-md-8 h1">Listado de clientes</div>
  </div>
  <div class="row">
    <div class="col-md-1 h3">ID</div>
    <div class="col-md-4 h3">Nombre</div>
    <div class="col-md-1 h3">Ciudad</div>
    <div class="col-md-1 h3">Categoria</div>
    <div class="col-md-3 h3 text-center">Operación</div>
  </div>
</div>

<div class="container bg-light">
  <%
    List<cliente> listado = (List<cliente>) request.getAttribute("listado");

    for (cliente cliente: listado) {
  %>
  <div id="<%=cliente.getId()%>" class="row mt-2 body">
    <div class="col-md-1 align-self-center"><%=cliente.getId() %>
    </div>
    <div class="col-md-4 align-self-center"><%= cliente.getApellido1() + " " + cliente.getApellido2() + ", " + cliente.getNombre()%>
    </div>
    <div class="col-md-1 align-self-center"><%=cliente.getCiudad() %>
    </div>
    <div class="col-md-1 align-self-center"><%=cliente.getCategoria() %>
    </div>


    <div class="col-md-3 align-self-center text-center">
      <form class="d-inline" method="get" action="ModificarClienteServlet">
        <input type="hidden" name="codigo" value="<%=cliente.getId() %>"/>
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
