package com.example.farabi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BukuLangka {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String judul;
    private String pengarang;
    private int stok;

    // Constructors
    public BukuLangka() {}
    public BukuLangka(String judul, String pengarang, int stok) {
        this.judul = judul;
        this.pengarang = pengarang;
        this.stok = stok;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getJudul() { return judul; }
    public void setJudul(String judul) { this.judul = judul; }
    public String getPengarang() { return pengarang; }
    public void setPengarang(String pengarang) { this.pengarang = pengarang; }
    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }
}
