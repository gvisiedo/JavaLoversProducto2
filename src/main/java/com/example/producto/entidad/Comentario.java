package com.example.producto.entidad;

import jakarta.persistence.*;

@Entity
@Table(name = "comentarios")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String email;
    private String cuerpo;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "publicacion_id",nullable = false)
    private PubliPedido publiPedido;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public PubliPedido getPubliPedido() {
        return publiPedido;
    }

    public void setPubliPedido(PubliPedido publiPedido) {
        this.publiPedido = publiPedido;
    }

    public Comentario() {
    }

}
