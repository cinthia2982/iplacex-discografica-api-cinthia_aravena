package org.iplacex.discografica.artistas;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "artistas")
public class Artista {

    @Id
    private String _id;
    private String nombre;
    private List<String> estilos;
    private int anioFundacion;
    private boolean estaActivo;

    // Getters y setters
}
