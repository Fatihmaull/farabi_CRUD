package com.example.farabi.controller;

import com.example.farabi.entity.BukuLangka;
import com.example.farabi.entity.Peminjaman;
import com.example.farabi.repository.BukuLangkaRepository;
import com.example.farabi.repository.PeminjamanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/peminjaman")
public class PeminjamanViewController {

    @Autowired
    private PeminjamanRepository peminjamanRepo;

    @Autowired
    private BukuLangkaRepository bukuRepo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("data", peminjamanRepo.findAll());
        return "peminjaman/list";
    }

    @GetMapping("/tambah")
    public String form(Model model) {
        model.addAttribute("peminjaman", new Peminjaman());
        model.addAttribute("bukuList", bukuRepo.findAll());
        return "peminjaman/form";
    }

    @PostMapping("/simpan")
    public String simpan(@ModelAttribute Peminjaman peminjaman) {
        peminjaman.setTanggalPinjam(LocalDate.now());
        peminjaman.setStatus("Menunggu");
        peminjamanRepo.save(peminjaman);
        return "redirect:/peminjaman";
    }

    @GetMapping("/setujui/{id}")
    public String setujui(@PathVariable Long id) {
        Peminjaman p = peminjamanRepo.findById(id).orElseThrow();
        p.setStatus("Disetujui");
        peminjamanRepo.save(p);
        return "redirect:/peminjaman";
    }

    @GetMapping("/tolak/{id}")
    public String tolak(@PathVariable Long id) {
        Peminjaman p = peminjamanRepo.findById(id).orElseThrow();
        p.setStatus("Ditolak");
        peminjamanRepo.save(p);
        return "redirect:/peminjaman";
    }
}
