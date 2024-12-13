package com.example.examendwes.servlet;

import com.example.examendwes.DAO.*;
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
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "ModificarClienteServlet", value = "/ModificarClienteServlet")
public class ModificarClienteServlet extends HttpServlet {

    private clienteDAO clienteDAO = new clienteDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = null;
        Optional<cliente> cliente = clienteDAO.find(Integer.parseInt(request.getParameter("codigo")));
        if (cliente.isPresent()) {
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/FormModificarCliente.jsp");
            request.setAttribute("cliente", cliente.get());
        } else {
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ListadoClientes.jsp");
        }
        dispatcher.forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cliente cliente = new cliente();
        cliente.setId(Integer.parseInt(request.getParameter("id")));
        cliente.setNombre(request.getParameter("nombre"));
        cliente.setApellido1(request.getParameter("apellido1"));
        cliente.setApellido2(request.getParameter("apellido2"));
        cliente.setCiudad(request.getParameter("ciudad"));
        cliente.setCategoria(Integer.parseInt(request.getParameter("categoria")));


        clienteDAO.update(cliente);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ListadoClientes.jsp");

        List<cliente> listadoCliente = this.clienteDAO.getAll();

        request.setAttribute("listado", listadoCliente);

        dispatcher.forward(request, response);
    }

}