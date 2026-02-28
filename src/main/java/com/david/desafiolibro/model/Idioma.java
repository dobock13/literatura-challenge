package com.david.desafiolibro.model;

public enum Idioma {
    ESPAÑOL("es", "Español"),
    INGLES("en", "Inglés"),
    FRANCES("fr", "Francés"),
    PORTUGUES("pt", "Portugués"),
    LATIN("la", "Latín"),
    ITALIANO("it","Italiano");

    private String idiomaApi;

    private String idiomaEspanol;


    Idioma (String idiomaApi,String idiomaEspanol){
        this.idiomaApi = idiomaApi;
        this.idiomaEspanol = idiomaEspanol;
    }
    public static Idioma fromString(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.idiomaApi.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ningún idioma encontrado para el código: " + text);
    }

    public static Idioma fromTotalEspanol(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.idiomaEspanol.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Idioma no reconocido: " + text);
    }
}

