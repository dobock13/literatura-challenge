package com.david.desafiolibro.service;

public interface IConvierteDatos {
    <T>T obtenerDatos(String json, Class<T> clase);
}
