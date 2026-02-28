package com.david.desafiolibro.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;
    @Enumerated(EnumType.STRING)
    private Idioma idioma;

    private Double numeroDescargas;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="autor_id")
    private Autor autor;

    public Libro(){}

    public Libro (DatosLibros datosLibros, Autor autor){
        this.titulo = datosLibros.titulo();
        this.idioma= Idioma.fromString(datosLibros.idiomas().get(0));
        this.numeroDescargas = datosLibros.numeroDescargas();
        this.autor = autor;
    }

    @Override
    public String toString() {
        return String.format("""
                ------- LIBRO -------
                Titulo: %s
                Autor: %s
                Idioma: %s
                Numero de descargas: %.1f
                ---------------------
                """, titulo, autor.getNombre(), idioma, numeroDescargas);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }
}
