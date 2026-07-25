package minimarket.model;

public class Admin extends User {

    public Admin(int id, String username, String password, String nama) {
        super(id, username, password, nama, "admin");
    }

    // Hak akses khusus Admin
    public void tambahBarang(minimarket.repository.BarangRepository repo, Barang barang) {
        repo.tambah(barang);
        System.out.println("Barang '" + barang.getNamaBarang() + "' berhasil ditambahkan.");
    }

    public void editHarga(Barang barang, double hargaBaru) {
        double hargaLama = barang.getHarga();
        barang.setHarga(hargaBaru);
        System.out.printf("Harga '%s' diubah dari Rp%,.0f → Rp%,.0f%n",
                barang.getNamaBarang(), hargaLama, hargaBaru);
    }

    public void updateStok(Barang barang, int stokBaru) {
        int stokLama = barang.getStok();
        barang.setStok(stokBaru);
        System.out.printf("Stok '%s' diubah dari %d → %d%n",
                barang.getNamaBarang(), stokLama, stokBaru);
    }

    public boolean hapusBarang(minimarket.repository.BarangRepository repo, String kodeBarang) {
        boolean berhasil = repo.hapus(kodeBarang);
        if (berhasil)
            System.out.println("Barang '" + kodeBarang + "' berhasil dihapus.");
        else
            System.out.println("Barang '" + kodeBarang + "' tidak ditemukan.");
        return berhasil;
    }

    public void lihatLaporan(minimarket.repository.TransaksiRepository repo) {
        System.out.println("\n=== LAPORAN PENJUALAN ===");
        var list = repo.findAll();
        if (list.isEmpty()) {
            System.out.println("Belum ada transaksi.");
            return;
        }
        double totalPendapatan = 0;
        for (var trx : list) {
            System.out.printf("[%s] %s — Total: Rp%,.0f — Status: %s%n",
                    trx.getIdTransaksi(),
                    trx.getTanggalFormatted(),
                    trx.getTotal(),
                    trx.getStatus());
            if ("selesai".equals(trx.getStatus()))
                totalPendapatan += trx.getTotal();
        }
        System.out.printf("%nTotal Pendapatan: Rp%,.0f%n", totalPendapatan);
        System.out.printf("Total Transaksi : %d%n", list.size());
    }

    @Override
    public String toString() {
        return "[ADMIN] " + getNama() + " (" + getUsername() + ")";
    }
}