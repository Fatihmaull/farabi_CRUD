package com.example.farabi.controller;

import com.example.farabi.entity.BukuLangka;
import com.example.farabi.repository.BukuLangkaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buku")
public class BukuLangkaController {

    @Autowired
    private BukuLangkaRepository repo;

    @GetMapping
    public List<BukuLangka> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public BukuLangka create(@RequestBody BukuLangka buku) {
        return repo.save(buku);
    }

    @PutMapping("/{id}")
    public BukuLangka update(@PathVariable Long id, @RequestBody BukuLangka updated) {
        BukuLangka buku = repo.findById(id).orElseThrow();
        buku.setJudul(updated.getJudul());
        buku.setPengarang(updated.getPengarang());
        buku.setStok(updated.getStok());
        return repo.save(buku);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
