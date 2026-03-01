# 📚 LiterAlura — Challenge Alura Latam

##  Descripción del Proyecto
LiterAlura es un catálogo interactivo de libros que consume información en tiempo real desde la API pública Gutendex.  
El sistema permite buscar, registrar y consultar libros y autores, almacenando los datos de forma persistente en una base de datos relacional.

El proyecto está diseñado con una arquitectura por capas, persistencia con JPA y manejo estructurado de datos externos en formato JSON.

---

##  Funcionalidades

El sistema implementa un menú interactivo con control de errores que permite:

###  Buscar libros por título
- Consulta la API Gutendex.
- Registra automáticamente el libro y su autor si no existen en la base de datos.
- Evita duplicados mediante validación previa.

### Listar libros registrados
- Muestra todos los libros almacenados.
- Presenta información completa del ejemplar y su autor asociado.

### Listar autores registrados
- Visualiza autores guardados en la base de datos.
- Incluye información biográfica disponible.

### Filtro histórico de autores
- Permite buscar autores que estaban vivos en un año determinado.
- Implementa lógica de comparación con fechas almacenadas como Integer.

### Filtro por idioma
- Clasifica libros por idioma (en español y sin acentos).
- Facilita consultas segmentadas del catálogo.

---

## Arquitectura del Sistema
El proyecto sigue una estructura basada en capas:

- Capa de presentación: Menú interactivo en consola
- Capa de servicio: Lógica de negocio y consumo de API
- Capa de persistencia: Repositorios JPA
- Modelo de dominio: Entidades Libro y Autor

Relación implementada:
- Un autor → muchos libros

---

## Tecnologías Utilizadas

- Java 21
- Spring Boot 3+
- Spring Data JPA
- PostgreSQL
- Jackson (JSON parsing)
- Gutendex API

---

## Persistencia de Datos
Los datos consultados desde la API se almacenan localmente, permitiendo:

- Acceso rápido a información previamente buscada
- Evitar consultas repetidas a la API
- Gestión estructurada de relaciones entre entidades

---

##  Objetivo Académico
Este proyecto demuestra competencias en:

- Consumo de APIs REST
- Persistencia de datos con ORM
- Modelado de entidades y relaciones
- Manejo de Optional y Streams en Java
- Diseño modular de aplicaciones

