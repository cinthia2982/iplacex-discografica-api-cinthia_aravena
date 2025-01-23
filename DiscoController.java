package org.iplacex.discografica.discos;

import org.iplacex.discografica.artistas.IArtistaRepository;
import org.iplacex.discografica.artistas.Artista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class DiscoController {

    @Autowired
    private IDiscoRepository discoRepository;

    @Autowired
    private IArtistaRepository artistaRepository;

    @PostMapping("/disco")
    public ResponseEntity<Disco> handlePostDiscoRequest(@RequestBody Disco disco) {
        if (artistaRepository.existsById(disco.getIdArtista())) {
            Disco savedDisco = discoRepository.save(disco);
            return ResponseEntity.ok(savedDisco);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/discos")
    public ResponseEntity<List<Disco>> handleGetDiscosRequest() {
        List<Disco> discos = discoRepository.findAll();
        return ResponseEntity.ok(discos);
    }

    @GetMapping("/disco/{id}")
    public ResponseEntity<Disco> handleGetDiscoRequest(@PathVariable String id) {
        return discoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/artista/{id}/discos")
    public ResponseEntity<List<Disco>> handleGetDiscosByArtistaRequest(@PathVariable String id) {
        List<Disco> discos = discoRepository.findDiscosByIdArtista(id);
        return ResponseEntity.ok(discos);
    }
}
