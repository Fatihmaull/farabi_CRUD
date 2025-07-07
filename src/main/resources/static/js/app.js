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
// Buku - Form Tambah
// -------------------------
function setupFormBuku() {
  const form = document.querySelector("form#formBuku");
  if (!form) return;

  form.addEventListener("submit", function (e) {
    e.preventDefault();

    const formData = {
      judul: this.judul.value,
      pengarang: this.pengarang.value,
      stok: parseInt(this.stok.value)
    };

    fetch("/api/buku", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(formData)
    })
      .then(res => {
        if (res.ok) {
          alert("Buku berhasil ditambahkan");
          window.location.href = "/buku.html";
        } else {
          alert("Gagal menyimpan");
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

  // Isi dropdown buku
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

  // Submit form
  form.addEventListener("submit", function (e) {
    e.preventDefault();

    const payload = {
      namaSantri: this.namaSantri.value,
      buku: { id: parseInt(this.bukuId.value) },
      tanggalPinjam: this.tanggalPinjam.value,
      status: this.status.value
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
