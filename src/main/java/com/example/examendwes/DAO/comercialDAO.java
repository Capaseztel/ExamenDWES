package com.example.examendwes.DAO;

import com.example.examendwes.modelo.cliente;
import com.example.examendwes.modelo.comercial;

import java.util.List;
import java.util.Optional;

public interface comercialDAO {
    public List<comercial> getAll();
    public Optional<comercial> find(int id);
}
