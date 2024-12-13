package com.example.examendwes.DAO;

import com.example.examendwes.modelo.cliente;
import com.example.examendwes.modelo.pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class pedidoDAOImpl extends AbstractDAOImpl implements pedidoDAO{
    @Override
    public void create(pedido pedido) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("INSERT INTO pedido (total, fecha, id_cliente, id_comercial) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, pedido.getTotal());
            ps.setDate(2, pedido.getfecha());
            ps.setInt(3, pedido.getid_cliente());
            ps.setInt(4, pedido.getid_comercial());

            int rows = ps.executeUpdate();
            if (rows == 0)
                System.out.println("INSERT de socio con 0 filas insertadas.");

            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next())
                pedido.setId(rsGenKeys.getInt(1));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps ,rs);
        }
    }

    @Override
    public List<pedido> getAll() {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<pedido> listPedido = new ArrayList<>();

        try {
            conn = connectDB();

            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM pedido");
            while (rs.next()) {
                pedido pedido = new pedido();

                pedido.setId(rs.getInt("id"));
                pedido.setTotal(rs.getDouble("total"));
                pedido.setfecha(rs.getDate("fecha"));
                pedido.setid_cliente(rs.getInt("id_cliente"));
                pedido.setid_comercial(rs.getInt("id_comercial"));
                listPedido.add(pedido);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listPedido;
    }

    @Override
    public Optional<pedido> find(int id) {
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                conn = connectDB();

                ps = conn.prepareStatement("SELECT * FROM cliente WHERE id = ?");

                ps.setInt(1, id);

                rs = ps.executeQuery();

                if (rs.next()) {
                    pedido pedido = new pedido();

                    pedido.setId(rs.getInt("id"));
                    pedido.setTotal(rs.getDouble("total"));
                    pedido.setfecha(rs.getDate("fecha"));
                    pedido.setid_cliente(rs.getInt("id_cliente"));
                    pedido.setid_comercial(rs.getInt("id_comercial"));

                    return Optional.of(pedido);
                }

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                closeDb(conn, ps, rs);
            }

            return Optional.empty();
    }
}
