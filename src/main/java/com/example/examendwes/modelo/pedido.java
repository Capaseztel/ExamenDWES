package com.example.examendwes.modelo;

import java.sql.Date;

public class pedido {
    int id;
    double total;
    Date fecha;
    int id_cliente;
    int id_comercial;

    public pedido() {

    }

    public pedido(int id, double total, int id_cliente, int id_comercial) {
        this.id = id;
        this.total = total;
        this.id_cliente = id_cliente;
        this.id_comercial = id_comercial;
    }

    public pedido(int id, double total, Date fecha, int id_cliente, int id_comercial) {
        this.id = id;
        this.total = total;
        this.fecha = fecha;
        this.id_cliente = id_cliente;
        this.id_comercial = id_comercial;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Date getfecha() {
        return fecha;
    }

    public void setfecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getid_cliente() {
        return id_cliente;
    }

    public void setid_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getid_comercial() {
        return id_comercial;
    }

    public void setid_comercial(int id_comercial) {
        this.id_comercial = id_comercial;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
