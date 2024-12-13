package com.example.examendwes.servlet;

import com.example.examendwes.DAO.clienteDAOImpl;
import com.example.examendwes.DAO.clienteDAO;
import com.example.examendwes.modelo.cliente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListarClientesServlet", value = "/ListarClientesServlet")
public class ListarClientesServlet extends HttpServlet {
    private clienteDAO clienteDAO = new clienteDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ListadoClientes.jsp");

        List<cliente> listado =this.clienteDAO.getAll();
        request.setAttribute("listado", listado);

        dispatcher.forward(request, response);
    }
}
