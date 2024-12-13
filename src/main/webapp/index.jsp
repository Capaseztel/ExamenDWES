<html>
<head>
    <title>Redirigiendo...</title>
</head>
<body>
<h1>Que quieres consultar?</h1>
    <form method="get" action="ListarClientesServlet">
        <input type="submit" value="Clientes">
    </form>
    <form method="get" action="ListarPedidosServlet">
        <input type="submit" value="Pedidos">
    </form>
<%
    // response.sendRedirect("ListarPedidosServlet");
%>
</body>
</html>