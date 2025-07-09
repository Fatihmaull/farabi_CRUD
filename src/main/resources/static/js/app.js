// -------------------------
// Buku - List
// -------------------------
function loadBuku() {
  const tableBody = document.getElementById("bukuTable");
  if (!tableBody) return;

  fetch("/api/buku")
    .then(res => res.json())
    .then(data => {
      tableBody.innerHTML = "";
      data.forEach(b => {
        const row = `
          <tr>
            <td>${b.judul}</td>
            <td>${b.pengarang}</td>
            <td>${b.stok}</td>
            <td>
              <a href="/form-buku.html?id=${b.id}" class="btn btn-warning btn-sm">Edit</a>
              <button class="btn btn-danger btn-sm" onclick="hapusBuku(${b.id})">Hapus</button>
            </td>
          </tr>`;
        tableBody.innerHTML += row;
      });
    });
}

function hapusBuku(id) {
  if (!confirm("Yakin ingin menghapus buku ini?")) return;

  fetch(`/api/buku/${id}`, { method: "DELETE" })
    .then(() => location.reload())
    .catch(() => alert("Gagal menghapus buku"));
}

// -------------------------
// Buku - Form Tambah/Edit
// -------------------------
function setupFormBuku() {
  const form = document.querySelector("form#formBuku");
  if (!form) return;

  const params = new URLSearchParams(window.location.search);
  const id = params.get("id");

  if (id) {
    // Mode edit - ambil data dari API
    fetch(`/api/buku`)
      .then(res => res.json())
      .then(data => {
        const buku = data.find(b => b.id == id);
        if (!buku) return alert("Buku tidak ditemukan");

        form.judul.value = buku.judul;
        form.pengarang.value = buku.pengarang;
        form.stok.value = buku.stok;
      });
  }

  form.addEventListener("submit", function (e) {
    e.preventDefault();

    const payload = {
      judul: form.judul.value,
      pengarang: form.pengarang.value,
      stok: parseInt(form.stok.value)
    };

    const method = id ? "PUT" : "POST";
    const url = id ? `/api/buku/${id}` : "/api/buku";

    fetch(url, {
      method: method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload)
    })
      .then(res => {
        if (res.ok) {
          alert(`Buku berhasil ${id ? "diperbarui" : "ditambahkan"}`);
          window.location.href = "/buku.html";
        } else {
          alert("Gagal menyimpan data");
        }
      });
  });
}

// -------------------------
// Peminjaman - List
// -------------------------
function loadPeminjaman() {
  const tbody = document.getElementById("peminjamanTable");
  if (!tbody) return;

  fetch("/api/peminjaman")
    .then(res => res.json())
    .then(data => {
      tbody.innerHTML = "";
      data.forEach(p => {
        const row = `
          <tr>
            <td>${p.namaSantri}</td>
            <td>${p.buku?.judul || "-"}</td>
            <td>${p.tanggalPinjam}</td>
            <td>${p.status}</td>
            <td>
              <button class="btn btn-danger btn-sm" onclick="hapusPeminjaman(${p.id})">Hapus</button>
            </td>
          </tr>`;
        tbody.innerHTML += row;
      });
    });
}

function hapusPeminjaman(id) {
  if (!confirm("Hapus data ini?")) return;

  fetch(`/api/peminjaman/${id}`, { method: "DELETE" })
    .then(() => location.reload())
    .catch(() => alert("Gagal hapus"));
}

// -------------------------
// Peminjaman - Form Tambah
// -------------------------
function setupFormPeminjaman() {
  const form = document.querySelector("form#formPeminjaman");
  const select = document.querySelector("select[name='bukuId']");
  if (!form || !select) return;

  fetch("/api/peminjaman/buku")
    .then(res => res.json())
    .then(data => {
      data.forEach(b => {
        const option = document.createElement("option");
        option.value = b.id;
        option.textContent = b.judul;
        select.appendChild(option);
      });
    });

  form.addEventListener("submit", function (e) {
    e.preventDefault();

    const payload = {
      namaSantri: form.namaSantri.value,
      buku: { id: parseInt(form.bukuId.value) },
      tanggalPinjam: form.tanggalPinjam.value,
      status: form.status.value
    };

    fetch("/api/peminjaman", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload)
    })
      .then(res => {
        if (res.ok) {
          alert("Data berhasil disimpan");
          window.location.href = "/peminjaman.html";
        } else {
          alert("Gagal menyimpan");
        }
      });
  });
}

// -------------------------
// Init Script
// -------------------------
document.addEventListener("DOMContentLoaded", function () {
  loadBuku();
  setupFormBuku();
  loadPeminjaman();
  setupFormPeminjaman();
});
