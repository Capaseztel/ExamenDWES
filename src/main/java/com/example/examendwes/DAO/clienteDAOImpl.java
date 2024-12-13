package com.example.examendwes.DAO;

import com.example.examendwes.modelo.cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class clienteDAOImpl extends AbstractDAOImpl implements clienteDAO{
    @Override
    public void create(cliente cliente) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("INSERT INTO cliente (nombre, apellido1, apellido2, ciudad, categoria) values (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido1());
            ps.setString(3, cliente.getApellido2());
            ps.setString(4, cliente.getCiudad());
            ps.setInt(5, cliente.getCategoria());

            int rows = ps.executeUpdate();
            if (rows == 0)
                System.out.println("INSERT de socio con 0 filas insertadas.");

            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next())
                cliente.setId(rsGenKeys.getInt(1));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps ,rs);
        }
    }

    @Override
    public List<cliente> getAll() {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<cliente> listCliente = new ArrayList<>();

        try {
            conn = connectDB();

            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM cliente");
            while (rs.next()) {
                cliente cliente = new cliente();

                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido1(rs.getString("apellido1"));
                cliente.setApellido2(rs.getString("apellido2"));
                cliente.setCiudad(rs.getString("ciudad"));
                cliente.setCategoria(rs.getInt("categoria"));
                listCliente.add(cliente);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listCliente;
    }

    @Override
    public Optional<cliente> find(int id) {
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                conn = connectDB();

                ps = conn.prepareStatement("SELECT * FROM cliente WHERE id = ?");

                ps.setInt(1, id);

                rs = ps.executeQuery();

                if (rs.next()) {
                    cliente cliente = new cliente();

                    cliente.setId(rs.getInt("id"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setApellido1(rs.getString("apellido1"));
                    cliente.setApellido2(rs.getString("apellido2"));
                    cliente.setCiudad(rs.getString("ciudad"));
                    cliente.setCategoria(rs.getInt("categoria"));

                    return Optional.of(cliente);
                }

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                closeDb(conn, ps, rs);
            }

            return Optional.empty();
    }

    @Override
    public void update(cliente cliente) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("UPDATE cliente SET nombre = ?, apellido1 = ?, apellido2 = ?, ciudad = ?, categoria = ? where id = ?");

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido1());
            ps.setString(3, cliente.getApellido2());
            ps.setString(4, cliente.getCiudad());
            ps.setInt(5, cliente.getCategoria());
            ps.setInt(6, cliente.getId());

            int rows = ps.executeUpdate();
            if (rows == 0)
                System.out.println("INSERT de socio con 0 filas insertadas.");

            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next())
                cliente.setId(rsGenKeys.getInt(1));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps ,rs);
        }
    }

}
