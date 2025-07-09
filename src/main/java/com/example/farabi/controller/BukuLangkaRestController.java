package com.example.farabi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.farabi.entity.BukuLangka;
import com.example.farabi.repository.BukuLangkaRepository;

@RestController
@RequestMapping("/api/buku")
public class BukuLangkaRestController {

    @Autowired
    private BukuLangkaRepository bukuRepo;

    // GET all books
    @GetMapping
    public List<BukuLangka> getAllBuku() {
        return bukuRepo.findAll();
    }

    // POST a new book
    @PostMapping
    public BukuLangka tambahBuku(@RequestBody BukuLangka buku) {
        return bukuRepo.save(buku);
    }

    // PUT to update book
    @PutMapping("/{id}")
    public ResponseEntity<BukuLangka> updateBuku(@PathVariable Long id, @RequestBody BukuLangka updatedBuku) {
        return bukuRepo.findById(id)
                .map(buku -> {
                    buku.setJudul(updatedBuku.getJudul());
                    buku.setPengarang(updatedBuku.getPengarang());
                    buku.setStok(updatedBuku.getStok());
                    BukuLangka updated = bukuRepo.save(buku);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE a book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> hapusBuku(@PathVariable Long id) {
        if (!bukuRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        bukuRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
