package com.example.producto.dto;

import java.util.List;

public class PubliPedidoRespuesta {

    private List<PubliPedidoDto> contenido;
    private int numeroPagina;
    private int medidaPagina;
    private long totalElementos;
    private int totalPaginas;
    private boolean ultimo;

    public List<PubliPedidoDto> getContenido() {
        return contenido;
    }

    public void setContenido(List<PubliPedidoDto> contenido) {
        this.contenido = contenido;
    }

    public int getNumeroPagina() {
        return numeroPagina;
    }

    public void setNumeroPagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    public int getMedidaPagina() {
        return medidaPagina;
    }

    public void setMedidaPagina(int medidaPagina) {
        this.medidaPagina = medidaPagina;
    }

    public long getTotalElementos() {
        return totalElementos;
    }

    public void setTotalElementos(long totalElementos) {
        this.totalElementos = totalElementos;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public boolean isUltimo() {
        return ultimo;
    }

    public void setUltimo(boolean ultimo) {
        this.ultimo = ultimo;
    }

    public PubliPedidoRespuesta() {
    }
}
