package com.example.examendwes.DAO;

import com.example.examendwes.modelo.cliente;

import java.util.List;
import java.util.Optional;

public interface clienteDAO {
    public void create(cliente cliente);

    public List<cliente> getAll();
    public Optional<cliente> find(int id);

    public void update(cliente cliente);
}
