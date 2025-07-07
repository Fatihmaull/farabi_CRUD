package com.example.farabi.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.example.farabi.entity.BukuLangka; // ✅ Tambahkan ini!

@Entity
public class Peminjaman {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namaSantri;
    private LocalDate tanggalPinjam;
    private String status;

    @ManyToOne
    @JoinColumn(name = "buku_id")
    private BukuLangka buku;

    // Constructor
    public Peminjaman() {}

    // Getters & Setters
    public Long getId() { return id; }
    public String getNamaSantri() { return namaSantri; }
    public void setNamaSantri(String namaSantri) { this.namaSantri = namaSantri; }
    public LocalDate getTanggalPinjam() { return tanggalPinjam; }
    public void setTanggalPinjam(LocalDate tanggalPinjam) { this.tanggalPinjam = tanggalPinjam; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public BukuLangka getBuku() { return buku; }
    public void setBuku(BukuLangka buku) { this.buku = buku; }
}
