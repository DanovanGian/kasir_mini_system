<div align="center">

```
███████╗████████╗ ██████╗ ██████╗ ███████╗
██╔════╝╚══██╔══╝██╔═══██╗██╔══██╗██╔════╝
███████╗   ██║   ██║   ██║██████╔╝█████╗  
╚════██║   ██║   ██║   ██║██╔══██╗██╔══╝  
███████║   ██║   ╚██████╔╝██║  ██║███████╗
╚══════╝   ╚═╝    ╚═════╝ ╚═╝  ╚═╝╚══════╝
```

# Sistem Kasir Minimarket 

**Aplikasi kasir berbasis Java — dibangun dari nol, tanpa framework**

[![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com)
[![OOP](https://img.shields.io/badge/Paradigma-OOP-blue?style=for-the-badge)](https://en.wikipedia.org/wiki/Object-oriented_programming)
[![Status](https://img.shields.io/badge/Status-Active-brightgreen?style=for-the-badge)]()
[![Version](https://img.shields.io/badge/Version-1.0-purple?style=for-the-badge)]()
[![License](https://img.shields.io/badge/License-Academic-lightgrey?style=for-the-badge)]()

<img src="https://readme-typing-svg.demolab.com?font=Fira+Code&pause=1000&color=00D4AA&center=true&vCenter=true&width=600&lines=Sistem+Kasir+berbasis+Java+Console;Pure+OOP+%E2%80%94+tanpa+framework;Admin+%26+Kasir+role+system;Struk+otomatis+%26+manajemen+stok" alt="Typing SVG" />

</div>

---

## Tentang Project

> Minimarket DEL membutuhkan sistem kasir yang cepat, akurat, dan mudah digunakan. Project ini menjawab kebutuhan itu — dibangun dari nol menggunakan **Java murni**, tanpa framework apapun.

Ini bukan sekadar project kuliah biasa. Arsitekturnya dirancang layaknya aplikasi profesional:

```
┌─────────────────────────────────────────────────────────┐
│                    MINIMARKET DEL                        │
├──────────────────────────┬──────────────────────────────┤
│       ADMIN PANEL        │       KASIR PANEL            │
├──────────────────────────┼──────────────────────────────┤
│  ✦ CRUD Barang           │  ✦ Transaksi real-time       │
│  ✦ Update stok           │  ✦ Keranjang belanja         │
│  ✦ Laporan penjualan     │  ✦ Hitung kembalian          │
│  ✦ Barang terlaku        │  ✦ Cetak struk               │
│  ✦ Alert stok menipis    │  ✦ Stok update otomatis      │
└──────────────────────────┴──────────────────────────────┘
```

---

## Tech Stack

<div align="center">

| Layer | Teknologi | Keterangan |
|-------|-----------|------------|
| Bahasa | `Java 17+` | Switch expression, enhanced for loop |
| Paradigma | `Pure OOP` | Class, Inheritance, Encapsulation, Polymorphism |
| Storage | `ArrayList` | In-memory (upgrade ke MySQL di v3) |
| UI | `Java Console` | Text-based, upgrade ke Swing di v2 |
| Build | `javac` | Tanpa Maven/Gradle |
| Framework | `—` | Tidak ada. Semua dari nol. |

</div>

---

## Arsitektur

Project ini menggunakan arsitektur berlapis (**Layered Architecture**) — sama seperti aplikasi profesional:

```
┌─────────────────────────────────────┐
│             UI Layer                │  ← MenuUtama, MenuAdmin,
│    (Tampilan & interaksi user)      │     MenuKasir, MenuTransaksi
├─────────────────────────────────────┤
│           Service Layer             │  ← AuthService, BarangService,
│       (Logika bisnis & validasi)    │     TransaksiService, LaporanService
├─────────────────────────────────────┤
│         Repository Layer            │  ← UserRepository, BarangRepository,
│       (Akses & simpan data)         │     TransaksiRepository
├─────────────────────────────────────┤
│           Model Layer               │  ← User, Admin, Kasir, Barang,
│     (Entitas & struktur data)       │     Transaksi, DetailTransaksi
└─────────────────────────────────────┘
```

---

## Struktur Folder

```
kasir_mini_system/
│
├── 📁 src/minimarket/
│   ├── 📄 Main.java
│   │
│   ├── 📁 model/              # Entitas OOP
│   │   ├── User.java          # Parent class
│   │   ├── Admin.java         # extends User
│   │   ├── Kasir.java         # extends User
│   │   ├── Barang.java
│   │   ├── Transaksi.java
│   │   ├── DetailTransaksi.java
│   │   └── Pembayaran.java
│   │
│   ├── 📁 repository/         # Akses data
│   │   ├── UserRepository.java
│   │   ├── BarangRepository.java
│   │   └── TransaksiRepository.java
│   │
│   ├── 📁 service/            # Logika bisnis
│   │   ├── AuthService.java
│   │   ├── BarangService.java
│   │   ├── TransaksiService.java
│   │   └── LaporanService.java
│   │
│   ├── 📁 ui/                 # Tampilan console
│   │   ├── MenuUtama.java
│   │   ├── MenuAdmin.java
│   │   ├── MenuKasir.java
│   │   └── MenuTransaksi.java
│   │
│   └── 📁 util/               # Helper
│       ├── InputHelper.java
│       ├── FormatHelper.java
│       └── StrukPrinter.java
│
├── 📁 bin/                    # Hasil compile
├── 📁 docs/                   # Dokumentasi
└── 📄 README.md
```

---

## Cara Menjalankan

### Prasyarat

Pastikan Java sudah terinstall:

```bash
java -version
# output: openjdk version "17.x.x" atau lebih baru
```

### Clone & Setup

```bash
# Clone repository
git clone https://github.com/DanovanGian/kasir-minimarket-del.git
cd kasir-minimarket-del

# Buat folder bin
mkdir -p bin
```

### Compile

```bash
# macOS / Linux
find src -name "*.java" | xargs javac -d bin

# Windows
dir /s /b src\*.java > sources.txt && javac -d bin @sources.txt
```

### Jalankan

```bash
java -cp bin minimarket.Main
```

### Tampilan Awal

```
╔════════════════════════════════╗
║     SISTEM KASIR MINIMARKET   ║
║           DEL Store            ║
╚════════════════════════════════╝

=== LOGIN ===
Username : 
Password : 
```

---

## Akun Default

| Role | Username | Password | Akses |
|------|----------|----------|-------|
| 👑 Admin | `admin` | `admin123` | Penuh — CRUD + Laporan |
| 🧾 Kasir | `kasir1` | `kasir123` | Transaksi + Lihat barang |
| 🧾 Kasir | `kasir2` | `kasir123` | Transaksi + Lihat barang |

> Data bersifat **in-memory** — akan reset saat program ditutup. Fitur penyimpanan permanen hadir di **v3**.

---

## Alur Sistem

### Login & Navigasi

```
Program Start
     │
     ▼
┌─────────┐    gagal 3x    ┌──────────┐
│  LOGIN  │ ─────────────► │  EXIT    │
└─────────┘                └──────────┘
     │ berhasil
     ▼
┌─────────────────────┐
│    Cek Role         │
└──────┬──────────────┘
       │
  ┌────┴────┐
  ▼         ▼
Admin     Kasir
Menu      Menu
```

### Alur Transaksi Kasir

```
Transaksi Baru
     │
     ▼
Input Kode Barang ──► Tidak ditemukan? ──► Input ulang
     │
     ▼
Cek Stok ──────────► Stok kurang? ──────► Input ulang
     │
     ▼
Masuk Keranjang
     │
     ├──► Tambah barang lagi? ──► kembali ke atas
     │
     ▼
Input Uang Bayar ──► Kurang? ──────────► Input ulang
     │
     ▼
Hitung Kembalian
     │
     ├──► Cetak Struk
     └──► Kurangi Stok Otomatis
```

### Contoh Struk

```
════════════════════════════════════════
            MINIMARKET DEL
         Jl. Sudirman No. 1
════════════════════════════════════════
Kasir   : Budi Santoso
Tanggal : 01/06/2025 10:30:00
No. Trx : TRX-0001
────────────────────────────────────────
Indomie Goreng
    2 x Rp3.500             Rp7.000
Teh Botol 350ml
    1 x Rp5.000             Rp5.000
Chitato 75g
    1 x Rp10.000           Rp10.000
────────────────────────────────────────
Total         :            Rp22.000
Bayar         :            Rp50.000
Kembalian     :            Rp28.000
════════════════════════════════════════
          Terima Kasih! 
    Selamat Berbelanja Kembali :)
════════════════════════════════════════
```

---

## Konsep OOP yang Diterapkan

```
                        ┌──────────┐
                        │   User   │  ← Parent class
                        │ username │     encapsulation
                        │ password │     (private fields)
                        │   role   │
                        └────┬─────┘
                             │ inheritance
               ┌─────────────┴─────────────┐
               ▼                           ▼
          ┌─────────┐                ┌─────────┐
          │  Admin  │                │  Kasir  │
          │ +CRUD   │                │ +Trx    │
          │ +Laporan│                │ +Struk  │
          └─────────┘                └─────────┘
```

| Konsep OOP | Diterapkan Di | Cara |
|------------|--------------|------|
| **Encapsulation** | Semua model class | Field `private` + getter/setter |
| **Inheritance** | `Admin`, `Kasir` | `extends User` |
| **Polymorphism** | Semua model | Override `toString()` |
| **Abstraction** | Service layer | Sembunyikan logika dari UI |
| **Association** | `Transaksi` → `Kasir` | Kasir memiliki Transaksi |
| **Composition** | `Transaksi` ← `DetailTransaksi` | Detail tidak bisa hidup tanpa Transaksi |

---

## Dokumentasi

| Dokumen | Deskripsi |
|---------|-----------|
| 📋 [SRS](docs/SRS_Minimarket.md) | Software Requirements Specification lengkap |
| 📖 [User Story](docs/User_Story.md) | 17 user stories, 4 epic, sprint planning |
| 🗄️ [Schema MySQL](docs/schema_mysql.sql) | DDL lengkap + seed data + views |
| 🗺️ [Roadmap](docs/ROADMAP_MINGGUAN.md) | Rencana pengerjaan 6 minggu |
| 📁 [Struktur Folder](docs/STRUKTUR_FOLDER.md) | Penjelasan tiap layer |

---

## Roadmap Pengembangan

```
v1.0  ████████████████████  DONE   Console + ArrayList
v2.0  ░░░░░░░░░░░░░░░░░░░░  TODO   GUI Java Swing
v3.0  ░░░░░░░░░░░░░░░░░░░░  TODO   MySQL + JDBC
v4.0  ░░░░░░░░░░░░░░░░░░░░  TODO   Barcode + Dashboard grafik
```

### Yang akan datang di v2 (Swing GUI)
- Form login dengan antarmuka grafis
- Tabel barang dengan JTable
- Panel keranjang belanja interaktif
- Dialog pembayaran dengan konfirmasi

### Yang akan datang di v3 (MySQL)
- Data tersimpan permanen di database
- Login multi-user bersamaan
- Laporan dengan query SQL
- Export laporan ke file

---

## Kontributor

<div align="center">

| | Nama | GitHub |
|-|------|--------|
| 👤 | **Gian** | [@DanovanGian](https://github.com/DanovanGian) |

</div>

---

<div align="center">

**Dibangun dengan ☕ Java dan semangat belajar OOP**

*Institut Teknologi Del — Portfolio Project 2025*

</div>