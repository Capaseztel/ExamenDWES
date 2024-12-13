package com.example.examendwes.DAO;

import com.example.examendwes.modelo.comercial;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class comercialDAOImpl extends AbstractDAOImpl implements comercialDAO{
    @Override
    public List<comercial> getAll() {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<comercial> listComercial = new ArrayList<>();

        try {
            conn = connectDB();

            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM comercial");
            while (rs.next()) {
                comercial comercial = new comercial();

                comercial.setId(rs.getInt("id"));
                comercial.setNombre(rs.getString("nombre"));
                comercial.setApellido1(rs.getString("apellido1"));
                comercial.setApellido2(rs.getString("apellido2"));
                comercial.setComision(rs.getFloat("comision"));
                listComercial.add(comercial);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listComercial;
    }

    @Override
    public Optional<comercial> find(int id) {
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                conn = connectDB();

                ps = conn.prepareStatement("SELECT * FROM comercial WHERE id = ?");

                ps.setInt(1, id);

                rs = ps.executeQuery();

                if (rs.next()) {
                    comercial comercial = new comercial();

                    comercial.setId(rs.getInt("id"));
                    comercial.setNombre(rs.getString("nombre"));
                    comercial.setApellido1(rs.getString("apellido1"));
                    comercial.setApellido2(rs.getString("apellido2"));
                    comercial.setComision(rs.getFloat("comision"));

                    return Optional.of(comercial);
                }

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                closeDb(conn, ps, rs);
            }

            return Optional.empty();
    }


}
