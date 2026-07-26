package minimarket.service;

import minimarket.model.*;
import minimarket.repository.BarangRepository;
import minimarket.repository.TransaksiRepository;
import minimarket.util.StrukPrinter;

public class TransaksiService {

    private BarangRepository barangRepo;
    private TransaksiRepository transaksiRepo;
    private int nextIdTrx = 1;

    public TransaksiService(BarangRepository barangRepo,
            TransaksiRepository transaksiRepo) {
        this.barangRepo = barangRepo;
        this.transaksiRepo = transaksiRepo;
    }

    // -------------------------------------------------------
    // 1. Buat transaksi baru
    // -------------------------------------------------------
    public Transaksi buatTransaksiBaru(User kasir) {
        Transaksi trx = new Transaksi(nextIdTrx++, kasir);
        System.out.printf("Transaksi baru dibuat. [TRX-%04d]%n",
                trx.getIdTransaksi());
        return trx;
    }

    // -------------------------------------------------------
    // 2. Tambah barang ke keranjang
    // Kembalikan true jika berhasil, false jika ada masalah
    // -------------------------------------------------------
    public boolean tambahItem(Transaksi trx, String kodeBarang, int jumlah) {
        // Validasi jumlah
        if (jumlah <= 0) {
            System.out.println("Jumlah harus lebih dari 0.");
            return false;
        }

        // Cari barang
        Barang barang = barangRepo.findByKode(kodeBarang);
        if (barang == null) {
            System.out.println("Barang dengan kode '"
                    + kodeBarang + "' tidak ditemukan.");
            return false;
        }

        // Cek stok
        if (!barang.isStokTersedia(jumlah)) {
            System.out.printf("Stok tidak cukup! Tersedia: %d, diminta: %d.%n",
                    barang.getStok(), jumlah);
            return false;
        }

        // Masukkan ke keranjang
        trx.tambahDetail(new DetailTransaksi(barang, jumlah));
        System.out.printf("+ %-22s x%d  =  Rp%,.0f%n",
                barang.getNamaBarang(), jumlah, barang.getHarga() * jumlah);
        return true;
    }

    // -------------------------------------------------------
    // 3. Hapus item dari keranjang (by index, 0-based)
    // -------------------------------------------------------
    public boolean hapusItem(Transaksi trx, int index) {
        if (index < 0 || index >= trx.getDetailList().size()) {
            System.out.println("Nomor item tidak valid.");
            return false;
        }
        String nama = trx.getDetailList().get(index).getBarang().getNamaBarang();
        trx.hapusDetail(index);
        System.out.println("Item '" + nama + "' dihapus dari keranjang.");
        return true;
    }

    // -------------------------------------------------------
    // 4. Tampilkan keranjang ke console
    // -------------------------------------------------------
    public void tampilkanKeranjang(Transaksi trx) {
        System.out.println("\n--- KERANJANG BELANJA ---");
        if (trx.getDetailList().isEmpty()) {
            System.out.println("(Keranjang masih kosong)");
            return;
        }
        System.out.printf("%-4s %-22s %6s  %10s  %12s%n",
                "No.", "Nama Barang", "Jml", "Harga", "Subtotal");
        System.out.println("-".repeat(58));
        int no = 1;
        for (DetailTransaksi d : trx.getDetailList()) {
            System.out.printf("%-4d %-22s %6d  %10.0f  %12.0f%n",
                    no++,
                    d.getBarang().getNamaBarang(),
                    d.getJumlah(),
                    d.getHargaSatuan(),
                    d.getSubtotal());
        }
        System.out.println("-".repeat(58));
        System.out.printf("%-42s  %12.0f%n", "TOTAL", trx.getTotal());
    }

    // -------------------------------------------------------
    // 5. Proses pembayaran
    // Kembalikan objek Pembayaran jika sukses, null jika gagal
    // -------------------------------------------------------
    public Pembayaran prosesBayar(Transaksi trx, double uangBayar) {
        if (trx.getDetailList().isEmpty()) {
            System.out.println("Keranjang masih kosong. Tidak bisa membayar.");
            return null;
        }

        Pembayaran pembayaran = new Pembayaran(trx.getTotal(), uangBayar);

        if (!pembayaran.isValid()) {
            System.out.printf(
                    "Uang tidak cukup! Kurang: Rp%,.0f%n",
                    trx.getTotal() - uangBayar);
            return null;
        }

        // Kurangi stok semua barang dalam keranjang
        for (DetailTransaksi d : trx.getDetailList()) {
            d.getBarang().kurangiStok(d.getJumlah());
        }

        // Tandai selesai dan simpan
        trx.selesaikan();
        transaksiRepo.simpan(trx);

        return pembayaran;
    }

    // -------------------------------------------------------
    // 6. Batalkan transaksi (stok TIDAK dikurangi)
    // -------------------------------------------------------
    public void batalkanTransaksi(Transaksi trx) {
        trx.batal();
        System.out.println("Transaksi [TRX-"
                + String.format("%04d", trx.getIdTransaksi())
                + "] dibatalkan.");
    }

    // -------------------------------------------------------
    // 7. Cetak struk setelah bayar berhasil
    // -------------------------------------------------------
    public void cetakStruk(Transaksi trx, Pembayaran pembayaran) {
        StrukPrinter.cetak(trx, pembayaran);
    }

    // -------------------------------------------------------
    // Shortcut: lakukan bayar + cetak struk sekaligus
    // -------------------------------------------------------
    public boolean bayarDanCetakStruk(Transaksi trx, double uangBayar) {
        Pembayaran pembayaran = prosesBayar(trx, uangBayar);
        if (pembayaran == null)
            return false;
        cetakStruk(trx, pembayaran);
        return true;
    }
}