package com.example.examendwes.DAO;

import com.example.examendwes.modelo.cliente;
import com.example.examendwes.modelo.pedido;

import java.util.List;
import java.util.Optional;

public interface pedidoDAO {
    public void create(pedido pedido);

    public List<pedido> getAll();
    public Optional<pedido> find(int id);

}
