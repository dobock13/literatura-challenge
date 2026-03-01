package com.david.desafiolibro.Principal;

import com.david.desafiolibro.model.*;
import com.david.desafiolibro.repository.IAutorRepository;
import com.david.desafiolibro.repository.ILIbroRepository;
import com.david.desafiolibro.service.ConsumoAPI;
import com.david.desafiolibro.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private Scanner teclado = new Scanner(System.in);
    private ConvierteDatos conversor = new ConvierteDatos();
    private IAutorRepository autorRepositorio;
    private ILIbroRepository libroRepositorio;
    private Optional<Libro> libroBuscado;
    public Principal(ILIbroRepository librorepo, IAutorRepository autorrepo){
        this.libroRepositorio =librorepo;
        this.autorRepositorio= autorrepo;
    }

    public void muestraElMenu(){
        var opcion = -1;
        while (opcion != 0) {
            var menu = """ 
                    --------------------------
                    Elije la opcion a traves de su numero:
                    1 - Buscar libro por titulo 
                    2 - Buscar libros registrados
                    3 - listar autores registrados 
                    4- Buscar autores vivos en un determinado año 
                    5- listas libros por idioma
                  
                    0 - Salir
                    """;
            System.out.println(menu);
            try {
                String lectura = teclado.nextLine();
                opcion = Integer.parseInt(lectura);
            } catch (NumberFormatException e) {
                System.out.println("""
                    [!] ERROR: Entrada no válida. 
                    Por favor, escribe un número.
                    """);
                opcion = -1;
                continue;}

            switch (opcion) {
                case 1:
                    getLibrosporTitulo();
                    break;
                case 2:
                    buscarLibrosRegistrados();
                    break;
                case 3:
                    mostrarAutoresResgistrados();
                    break;
                case 4:
                    buscarAutoresporAño();
                    break;
                case 5:
                    buscarLibrosIdioma();
                    break;

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibrosIdioma() {
        System.out.println("""
                Escribe el idioma en el que deseas buscar
                * Español
                * Italiano*
                * Latin
                * Ingles
                * Frances
                * Portugues""");

        var idioma = teclado.nextLine().toLowerCase();
        Idioma lenguaje = Idioma.fromEspañol(idioma);

        if (idioma.equals("español") || idioma.equals("ingles") || idioma.equals("frances") ||
                idioma.equals("portugues") || idioma.equals("latin") || idioma.equals("italiano")) {

            List<Libro> librosPorIdioma = libroRepositorio.findByIdioma(lenguaje);

            if (librosPorIdioma.isEmpty()) {
                System.out.println(String.format("\n [!] No hay libros registrados en el idioma '%s' \n", idioma));
            } else {
                System.out.println(String.format("\n--- LIBROS EN EL IDIOMA [%s] ---", idioma.toUpperCase()));
                librosPorIdioma.forEach(System.out::println);
            }
        } else {
            System.out.println("Idioma no válido. Por favor, usa los códigos de la lista (es, en, fr, pt).");
        }
    }

    private void buscarAutoresporAño() {
        System.out.println("Escribe el año que deseas buscar");
        var año = teclado.nextInt();
        List<Autor> autoresVivos = autorRepositorio.buscarAutoresVivosEnDeterminadoAño(año);
        if(autoresVivos.isEmpty()){
            System.out.println(String.format("\n [!] No se encontraron autores vivos en el año %d \n", año));
        }else{
            System.out.println(String.format("\n--- AUTORES VIVOS EN EL AÑO %d ---", año));
            autoresVivos.forEach(System.out::println);
        }
    }

    private void mostrarAutoresResgistrados() {
       List<Autor> autores = autorRepositorio.findAll();
        autores.stream().forEach(a-> System.out.println(a));

    }

    private void buscarLibrosRegistrados() {
        List<Libro>libros = libroRepositorio.findAll();
        libros.stream().forEach(l-> System.out.println(l));

    }

    private Datos getLibrosWeb(String tituloLibro) {
        var json = consumoAPI.obtenerDatos(URL_BASE+"?search="+ tituloLibro.replace(" ","+"));
        // System.out.println(json);
        Datos datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        return datosBusqueda;
//
    }
    private void getLibrosporTitulo(){
        System.out.println("Escribe el libro que deseas buscar");
        var nombreLibro= teclado.nextLine();
        if (nombreLibro.isBlank()) {
            System.out.println("No ingresaste un nombre válido. Inténtalo de nuevo.");
            return;
        }

        Optional<Libro> libroLocal = libroRepositorio.findByTituloIgnoreCase(nombreLibro);

        if (libroLocal.isPresent()) {
            System.out.println("\n--- ESTE LIBRO YA ESTÁ REGISTRADO ---");
            return;
        }
        Datos datosBusqueda = getLibrosWeb(nombreLibro);
        if (datosBusqueda.resultados() == null || datosBusqueda.resultados().isEmpty()) {
            System.out.println("Libro no encontrado");
            return;
        } DatosLibros datosLibro = datosBusqueda.resultados().get(0);
        if (datosLibro.autores() == null || datosLibro.autores().isEmpty()) {
            System.out.println("No se encontró información del autor.");
            return;
        }

        DatosAutor datosAutor = datosLibro.autores().get(0);

        Autor autor = autorRepositorio.findByNombreIgnoreCase(datosAutor.nombre())
                .orElseGet(() -> autorRepositorio.save(new Autor(datosAutor)));

        Libro libroNuevo = new Libro(datosLibro, autor);

        autor.agregarLibro(libroNuevo);
        libroRepositorio.save(libroNuevo);
        System.out.println(libroNuevo);

    }
}