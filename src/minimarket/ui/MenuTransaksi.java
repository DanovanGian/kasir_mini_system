package minimarket.ui;

import minimarket.model.*;
import minimarket.repository.BarangRepository;
import minimarket.repository.TransaksiRepository;
import minimarket.service.TransaksiService;
import minimarket.util.InputHelper;

public class MenuTransaksi {

    private User kasir;
    private TransaksiService transaksiService;
    private InputHelper input;

    public MenuTransaksi(User kasir,
            BarangRepository barangRepo,
            TransaksiRepository transaksiRepo) {
        this.kasir = kasir;
        this.transaksiService = new TransaksiService(barangRepo, transaksiRepo);
        this.input = new InputHelper();
    }

    // -------------------------------------------------------
    // Entry point — dipanggil dari MenuKasir
    // -------------------------------------------------------
    public void mulaiTransaksi() {
        Transaksi trx = transaksiService.buatTransaksiBaru(kasir);

        boolean jalan = true;
        while (jalan) {
            transaksiService.tampilkanKeranjang(trx);
            tampilkanOpsi();

            int pilih = input.getInt("Pilihan: ");
            switch (pilih) {
                case 1 -> tambahBarang(trx);
                case 2 -> hapusItem(trx);
                case 3 -> prosesKonfirmasiPembayaran(trx);
                case 0 -> {
                    if (konfirmasiBatal()) {
                        transaksiService.batalkanTransaksi(trx);
                        jalan = false;
                    }
                }
                default -> System.out.println("Pilihan tidak valid.");
            }

            // Transaksi sudah selesai atau dibatalkan — keluar loop
            if ("selesai".equals(trx.getStatus())
                    || "batal".equals(trx.getStatus())) {
                jalan = false;
            }
        }
    }

    // -------------------------------------------------------
    // Sub-menu: tambah barang ke keranjang
    // -------------------------------------------------------
    private void tambahBarang(Transaksi trx) {
        System.out.println("\n--- TAMBAH BARANG ---");
        String kode = input.getString("Kode barang : ");
        int jumlah = input.getInt("Jumlah      : ");
        transaksiService.tambahItem(trx, kode.toUpperCase(), jumlah);
    }

    // -------------------------------------------------------
    // Sub-menu: hapus item dari keranjang
    // -------------------------------------------------------
    private void hapusItem(Transaksi trx) {
        if (trx.getDetailList().isEmpty()) {
            System.out.println("Keranjang sudah kosong.");
            return;
        }
        int no = input.getInt("Hapus item nomor: ");
        transaksiService.hapusItem(trx, no - 1); // UI 1-based, kode 0-based
    }

    // -------------------------------------------------------
    // Sub-menu: konfirmasi dan proses pembayaran
    // -------------------------------------------------------
    private void prosesKonfirmasiPembayaran(Transaksi trx) {
        if (trx.getDetailList().isEmpty()) {
            System.out.println("Keranjang masih kosong!");
            return;
        }

        System.out.printf("%nTotal yang harus dibayar: Rp%,.0f%n",
                trx.getTotal());

        double uangBayar = input.getDouble("Uang bayar    : Rp");

        boolean berhasil = transaksiService.bayarDanCetakStruk(trx, uangBayar);
        if (!berhasil) {
            System.out.println("Pembayaran gagal. Coba lagi.");
        }
    }

    // -------------------------------------------------------
    // Konfirmasi sebelum batal
    // -------------------------------------------------------
    private boolean konfirmasiBatal() {
        String jawab = input.getString(
                "Yakin batalkan transaksi? (y/n): ");
        return jawab.equalsIgnoreCase("y");
    }

    // -------------------------------------------------------
    // Tampilan opsi menu
    // -------------------------------------------------------
    private void tampilkanOpsi() {
        System.out.println("\n[1] Tambah barang");
        System.out.println("[2] Hapus item dari keranjang");
        System.out.println("[3] Bayar");
        System.out.println("[0] Batalkan transaksi");
    }
}