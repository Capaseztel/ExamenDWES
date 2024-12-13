package com.example.examendwes.servlet;

import com.example.examendwes.DAO.clienteDAOImpl;
import com.example.examendwes.DAO.clienteDAO;
import com.example.examendwes.DAO.comercialDAOImpl;
import com.example.examendwes.DAO.comercialDAO;
import com.example.examendwes.DAO.pedidoDAO;
import com.example.examendwes.DAO.pedidoDAOImpl;
import com.example.examendwes.modelo.cliente;
import com.example.examendwes.modelo.comercial;
import com.example.examendwes.modelo.pedido;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListarPedidosServlet", value = "/ListarPedidosServlet")
public class ListarPedidosServlet extends HttpServlet {

    private pedidoDAO pedidoDAO = new pedidoDAOImpl();
    private clienteDAO clienteDAO = new clienteDAOImpl();
    private comercialDAO comercialDAO = new comercialDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ListadoPedidos.jsp");

        List<pedido> listadoPedido = this.pedidoDAO.getAll();
        List<cliente> listadoCliente = this.clienteDAO.getAll();
        List<comercial> listadoComercial = this.comercialDAO.getAll();

        request.setAttribute("listadoPedido", listadoPedido);
        request.setAttribute("listadoCliente", listadoCliente);
        request.setAttribute("listadoComercial", listadoComercial);

        dispatcher.forward(request, response);
    }
}
