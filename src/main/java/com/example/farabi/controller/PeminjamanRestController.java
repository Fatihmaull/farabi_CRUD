package com.example.farabi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.farabi.entity.BukuLangka;
import com.example.farabi.entity.Peminjaman;
import com.example.farabi.repository.BukuLangkaRepository;
import com.example.farabi.repository.PeminjamanRepository;

@RestController
@RequestMapping("/api/peminjaman")
public class PeminjamanRestController {

    @Autowired
    private PeminjamanRepository peminjamanRepo;

    @Autowired
    private BukuLangkaRepository bukuRepo;

    @GetMapping
    public List<Peminjaman> getAllPeminjaman() {
        return peminjamanRepo.findAll();
    }

    @PostMapping
    public Peminjaman tambah(@RequestBody Peminjaman p) {
        return peminjamanRepo.save(p);
    }

    @DeleteMapping("/{id}")
    public void hapus(@PathVariable Long id) {
        peminjamanRepo.deleteById(id);
    }

    @GetMapping("/buku")
    public List<BukuLangka> daftarBuku() {
        return bukuRepo.findAll();
    }
}
