package minimarket.ui;
 
import minimarket.model.*;
import minimarket.repository.BarangRepository;
import minimarket.util.*;
 
public class MenuKasir {
    private User kasir;
    private BarangRepository barangRepo = new BarangRepository();
    private InputHelper input = new InputHelper();
    private int nextIdTrx = 1;
 
    public MenuKasir(User kasir) { this.kasir = kasir; }
 
    public void tampilkan() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== MENU KASIR ===");
            System.out.println("1. Transaksi Baru");
            System.out.println("2. Lihat Daftar Barang");
            System.out.println("0. Logout");
 
            int pilihan = input.getInt("Pilihan: ");
            switch (pilihan) {
                case 1 -> transaksi();
                case 2 -> lihatBarang();
                case 0 -> running = false;
                default -> System.out.println("Pilihan tidak valid!");
            }
        }
    }
 
    private void transaksi() {
        Transaksi trx = new Transaksi(nextIdTrx++, kasir);
        boolean tambahTerus = true;
 
        while (tambahTerus) {
            System.out.println("\n--- KERANJANG BELANJA ---");
            tampilKeranjang(trx);
 
            System.out.println("\n1. Tambah Barang");
            System.out.println("2. Bayar");
            System.out.println("0. Batal");
            int pilih = input.getInt("Pilihan: ");
 
            switch (pilih) {
                case 1 -> tambahKeKeranjang(trx);
                case 2 -> {
                    if (trx.getDetailList().isEmpty()) {
                        System.out.println("Keranjang masih kosong!");
                    } else {
                        bayar(trx);
                        tambahTerus = false;
                    }
                }
                case 0 -> { trx.batal(); tambahTerus = false; }
            }
        }
    }
 
    private void tambahKeKeranjang(Transaksi trx) {
        String kode = input.getString("Kode barang : ");
        Barang barang = barangRepo.findByKode(kode);
        if (barang == null) { System.out.println("Barang tidak ditemukan!"); return; }
 
        System.out.println("Barang: " + barang.getInfo());
        int jumlah = input.getInt("Jumlah      : ");
 
        if (!barang.isStokTersedia(jumlah)) {
            System.out.printf("Stok tidak cukup! Tersedia: %d%n", barang.getStok());
            return;
        }
 
        trx.tambahDetail(new DetailTransaksi(barang, jumlah));
        System.out.println("Berhasil ditambahkan ke keranjang!");
    }
 
    private void tampilKeranjang(Transaksi trx) {
        if (trx.getDetailList().isEmpty()) {
            System.out.println("(Keranjang kosong)");
        } else {
            trx.getDetailList().forEach(d ->
                System.out.printf("%-20s %3dx %-10.0f = %10.0f%n",
                    d.getBarang().getNamaBarang(), d.getJumlah(),
                    d.getHargaSatuan(), d.getSubtotal()));
            System.out.printf("TOTAL: Rp%,.0f%n", trx.getTotal());
        }
    }
 
    private void bayar(Transaksi trx) {
        System.out.printf("%nTotal yang harus dibayar: Rp%,.0f%n", trx.getTotal());
        double uangBayar = input.getDouble("Uang bayar : Rp");
        Pembayaran pembayaran = new Pembayaran(trx.getTotal(), uangBayar);
 
        if (!pembayaran.isValid()) {
            System.out.println("Uang tidak cukup!");
            return;
        }
 
        // Kurangi stok semua barang
        trx.getDetailList().forEach(d ->
            d.getBarang().kurangiStok(d.getJumlah()));
 
        trx.selesaikan();
        StrukPrinter.cetak(trx, pembayaran);
    }
 
    private void lihatBarang() {
        System.out.println("\n--- DAFTAR BARANG ---");
        barangRepo.findAll().forEach(b -> System.out.println(b.getInfo()));
    }
}