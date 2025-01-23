
package org.iplacex.discografica.artistas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ArtistaController {

    @Autowired
    private IArtistaRepository artistaRepository;

    @PostMapping("/artista")
    public ResponseEntity<Artista> handleInsertArtistaRequest(@RequestBody Artista artista) {
        Artista savedArtista = artistaRepository.save(artista);
        return ResponseEntity.ok(savedArtista);
    }

    @GetMapping("/artistas")
    public ResponseEntity<List<Artista>> handleGetAristasRequest() {
        List<Artista> artistas = artistaRepository.findAll();
        return ResponseEntity.ok(artistas);
    }

    @GetMapping("/artista/{id}")
    public ResponseEntity<Artista> handleGetArtistaRequest(@PathVariable String id) {
        return artistaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/artista/{id}")
    public ResponseEntity<Artista> handleUpdateArtistaRequest(@PathVariable String id, @RequestBody Artista artista) {
        return artistaRepository.findById(id)
                .map(existingArtista -> {
                    artista.set_id(id);
                    artistaRepository.save(artista);
                    return ResponseEntity.ok(artista);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/artista/{id}")
    public ResponseEntity<Void> handleDeleteArtistaRequest(@PathVariable String id) {
        return artistaRepository.findById(id)
                .map(existingArtista -> {
                    artistaRepository.delete(existingArtista);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
