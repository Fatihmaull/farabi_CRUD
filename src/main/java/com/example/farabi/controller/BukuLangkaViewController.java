package com.example.farabi.controller;

import com.example.farabi.entity.BukuLangka;
import com.example.farabi.repository.BukuLangkaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/buku")
public class BukuLangkaViewController {

    @Autowired
    private BukuLangkaRepository repo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("bukuList", repo.findAll());
        return "buku/list";
    }

    @GetMapping("/tambah")
    public String form(Model model) {
        model.addAttribute("buku", new BukuLangka());
        return "buku/form";
    }

    @PostMapping("/simpan")
    public String simpan(@ModelAttribute BukuLangka buku) {
        repo.save(buku);
        return "redirect:/buku";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        BukuLangka buku = repo.findById(id).orElseThrow();
        model.addAttribute("buku", buku);
        return "buku/form";
    }

    @GetMapping("/hapus/{id}")
    public String hapus(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/buku";
    }
}
