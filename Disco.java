package org.iplacex.discografica.discos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "discos")
public class Disco {

    @Id
    private String _id;
    private String idArtista;
    private String nombre;
    private int anioLanzamiento;
    private List<String> canciones;

    // Getters y setters
}

