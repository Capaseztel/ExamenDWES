package com.example.examendwes.servlet;

import com.example.examendwes.DAO.clienteDAOImpl;
import com.example.examendwes.DAO.comercialDAOImpl;
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
import com.example.examendwes.DAO.pedidoDAO;
import com.example.examendwes.DAO.comercialDAO;
import com.example.examendwes.DAO.clienteDAO;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static java.lang.Double.parseDouble;

@WebServlet(name = "CrearPedidoServlet", value = "/CrearPedidoServlet")
public class CrearPedidoServlet extends HttpServlet {

    private pedidoDAO pedidoDAO = new pedidoDAOImpl();
    private comercialDAO comercialDAO = new comercialDAOImpl();
    private clienteDAO clienteDAO = new clienteDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/FormCrearPedido.jsp");
        List<cliente> listadoCliente = this.clienteDAO.getAll();
        List<comercial> listadoComercial = this.comercialDAO.getAll();
        request.setAttribute("listaCliente", listadoCliente);
        request.setAttribute("listaComercial", listadoComercial);
        dispatcher.forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        pedido pedido = new pedido();
        pedido.setTotal(Double.parseDouble(request.getParameter("total")));
        pedido.setfecha(Date.valueOf(request.getParameter("fecha")));
        pedido.setid_cliente(Integer.parseInt(request.getParameter("cliente")));
        pedido.setid_comercial(Integer.parseInt(request.getParameter("comercial")));
        pedidoDAO.create(pedido);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ListadoPedidos.jsp");

        List<pedido> listadoPedido = this.pedidoDAO.getAll();
        List<cliente> listadoCliente = this.clienteDAO.getAll();
        List<comercial> listadoComercial = this.comercialDAO.getAll();

        request.setAttribute("listadoPedido", listadoPedido);
        request.setAttribute("listadoCliente", listadoCliente);
        request.setAttribute("listadoComercial", listadoComercial);
        request.setAttribute("newSocioID", pedido.getId());

        dispatcher.forward(request, response);
    }

}