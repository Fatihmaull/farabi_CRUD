package com.example.farabi.controller;

import com.example.farabi.entity.BukuLangka;
import com.example.farabi.entity.Peminjaman;
import com.example.farabi.repository.BukuLangkaRepository;
import com.example.farabi.repository.PeminjamanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/peminjaman")
public class PeminjamanController {

    @Autowired
    private PeminjamanRepository repo;

    @Autowired
    private BukuLangkaRepository bukuRepo;

    @PostMapping
    public Peminjaman ajukan(@RequestBody Map<String, Object> payload) {
        Long bukuId = Long.valueOf(payload.get("bukuId").toString());
        String namaSantri = payload.get("namaSantri").toString();

        BukuLangka buku = bukuRepo.findById(bukuId).orElseThrow();

        Peminjaman p = new Peminjaman();
        p.setNamaSantri(namaSantri);
        p.setBuku(buku);
        p.setTanggalPinjam(LocalDate.now());
        p.setStatus("Menunggu");

        return repo.save(p);
    }

    @PutMapping("/{id}/setujui")
    public Peminjaman setujui(@PathVariable Long id) {
        Peminjaman p = repo.findById(id).orElseThrow();
        p.setStatus("Disetujui");
        return repo.save(p);
    }

    @PutMapping("/{id}/tolak")
    public Peminjaman tolak(@PathVariable Long id) {
        Peminjaman p = repo.findById(id).orElseThrow();
        p.setStatus("Ditolak");
        return repo.save(p);
    }

    @GetMapping
    public List<Peminjaman> getAll() {
        return repo.findAll();
    }
}
