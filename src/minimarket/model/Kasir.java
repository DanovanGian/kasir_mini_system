package minimarket.model;

import minimarket.repository.BarangRepository;
import minimarket.repository.TransaksiRepository;

public class Kasir extends User {

    private int totalTransaksiHariIni = 0;
    private double totalPendapatanHariIni = 0;

    public Kasir(int id, String username, String password, String nama) {
        super(id, username, password, nama, "kasir");
    }

    /**
     * Membuat objek Transaksi baru yang siap diisi barang.
     */
    public Transaksi buatTransaksi(int idTransaksi) {
        Transaksi trx = new Transaksi(idTransaksi, this);
        System.out.printf("Transaksi baru dibuat. ID: TRX-%04d%n", idTransaksi);
        return trx;
    }

    /**
     * Menambahkan barang ke keranjang transaksi.
     * Melakukan validasi stok sebelum menambahkan.
     */
    public boolean tambahKeKeranjang(Transaksi trx, BarangRepository repo,
            String kodeBarang, int jumlah) {
        Barang barang = repo.findByKode(kodeBarang);
        if (barang == null) {
            System.out.println("Barang dengan kode '" + kodeBarang + "' tidak ditemukan!");
            return false;
        }
        if (!barang.isStokTersedia(jumlah)) {
            System.out.printf("Stok tidak cukup! Tersedia: %d, diminta: %d%n",
                    barang.getStok(), jumlah);
            return false;
        }
        trx.tambahDetail(new DetailTransaksi(barang, jumlah));
        System.out.printf("✓ %s x%d ditambahkan. Subtotal: Rp%,.0f%n",
                barang.getNamaBarang(), jumlah, barang.getHarga() * jumlah);
        return true;
    }

    /**
     * Memproses pembayaran dan mengurangi stok secara otomatis.
     * Mengembalikan objek Pembayaran jika sukses, null jika gagal.
     */
    public Pembayaran prosesPembayaran(Transaksi trx, double uangBayar,
            TransaksiRepository trxRepo) {
        if (trx.getDetailList().isEmpty()) {
            System.out.println("Keranjang masih kosong!");
            return null;
        }

        Pembayaran pembayaran = new Pembayaran(trx.getTotal(), uangBayar);
        if (!pembayaran.isValid()) {
            System.out.printf("Uang tidak cukup! Kurang: Rp%,.0f%n",
                    trx.getTotal() - uangBayar);
            return null;
        }

        // Kurangi stok semua barang dalam transaksi
        for (DetailTransaksi detail : trx.getDetailList()) {
            detail.getBarang().kurangiStok(detail.getJumlah());
        }

        // Tandai transaksi selesai & simpan
        trx.selesaikan();
        trxRepo.simpan(trx);

        // Update statistik kasir hari ini
        totalTransaksiHariIni++;
        totalPendapatanHariIni += trx.getTotal();

        return pembayaran;
    }

    /**
     * Mencetak struk ke console menggunakan StrukPrinter.
     */
    public void cetakStruk(Transaksi trx, Pembayaran pembayaran) {
        minimarket.util.StrukPrinter.cetak(trx, pembayaran);
    }

    // Getter statistik kasir
    public int getTotalTransaksiHariIni() {
        return totalTransaksiHariIni;
    }

    public double getTotalPendapatanHariIni() {
        return totalPendapatanHariIni;
    }

    public void lihatStatistik() {
        System.out.println("\n=== STATISTIK KASIR HARI INI ===");
        System.out.printf("Nama Kasir       : %s%n", getNama());
        System.out.printf("Total Transaksi  : %d%n", totalTransaksiHariIni);
        System.out.printf("Total Pendapatan : Rp%,.0f%n", totalPendapatanHariIni);
    }

    @Override
    public String toString() {
        return "[KASIR] " + getNama() + " (" + getUsername() + ")";
    }
}