package minimarket.service;
 
import minimarket.model.DetailTransaksi;
import minimarket.model.Transaksi;
import minimarket.repository.BarangRepository;
import minimarket.repository.TransaksiRepository;
 
import java.util.*;
import java.util.stream.Collectors;
 
public class LaporanService {
 
    private TransaksiRepository transaksiRepo;
    private BarangRepository    barangRepo;
 
    public LaporanService(TransaksiRepository transaksiRepo,
                          BarangRepository barangRepo) {
        this.transaksiRepo = transaksiRepo;
        this.barangRepo    = barangRepo;
    }
 
    // -------------------------------------------------------
    // 1. Riwayat semua transaksi
    // -------------------------------------------------------
    public void tampilkanRiwayatTransaksi() {
        List<Transaksi> list = transaksiRepo.findAll();
        System.out.println("\n" + "=".repeat(62));
        System.out.println("          RIWAYAT TRANSAKSI");
        System.out.println("=".repeat(62));
 
        if (list.isEmpty()) {
            System.out.println("Belum ada transaksi.");
            System.out.println("=".repeat(62));
            return;
        }
 
        System.out.printf("%-10s  %-20s  %12s  %-8s%n",
            "No. Trx", "Tanggal", "Total", "Status");
        System.out.println("-".repeat(62));
 
        for (Transaksi trx : list) {
            System.out.printf("TRX-%04d  %-20s  %12.0f  %-8s%n",
                trx.getIdTransaksi(),
                trx.getTanggalFormatted(),
                trx.getTotal(),
                trx.getStatus().toUpperCase());
        }
        System.out.println("=".repeat(62));
    }
 
    // -------------------------------------------------------
    // 2. Ringkasan total pendapatan
    // -------------------------------------------------------
    public void tampilkanRingkasan() {
        List<Transaksi> semua    = transaksiRepo.findAll();
        List<Transaksi> selesai  = transaksiRepo.findByStatus("selesai");
        List<Transaksi> batal    = transaksiRepo.findByStatus("batal");
        double total             = transaksiRepo.hitungTotalPendapatan();
 
        System.out.println("\n" + "=".repeat(40));
        System.out.println("       RINGKASAN PENJUALAN");
        System.out.println("=".repeat(40));
        System.out.printf("Total transaksi   : %d%n", semua.size());
        System.out.printf("Transaksi selesai : %d%n", selesai.size());
        System.out.printf("Transaksi batal   : %d%n", batal.size());
        System.out.println("-".repeat(40));
        System.out.printf("Total pendapatan  : Rp%,.0f%n", total);
        System.out.println("=".repeat(40));
    }
 
    // -------------------------------------------------------
    // 3. Barang paling laku (by jumlah terjual)
    // -------------------------------------------------------
    public void tampilkanBarangTerlaku(int topN) {
        // Hitung total terjual per barang dari semua transaksi selesai
        Map<String, int[]> rekap = new HashMap<>();
        // rekap: kodeBarang -> [totalJumlah, totalPendapatan(cast int untuk map)]
 
        for (Transaksi trx : transaksiRepo.findByStatus("selesai")) {
            for (DetailTransaksi d : trx.getDetailList()) {
                String kode = d.getBarang().getKodeBarang();
                rekap.computeIfAbsent(kode, k -> new int[]{0, 0});
                rekap.get(kode)[0] += d.getJumlah();
            }
        }
 
        // Untuk pendapatan, kita pakai map double terpisah
        Map<String, Double> pendapatan = new HashMap<>();
        for (Transaksi trx : transaksiRepo.findByStatus("selesai")) {
            for (DetailTransaksi d : trx.getDetailList()) {
                String kode = d.getBarang().getKodeBarang();
                pendapatan.merge(kode, d.getSubtotal(), Double::sum);
            }
        }
 
        if (rekap.isEmpty()) {
            System.out.println("Belum ada data penjualan.");
            return;
        }
 
        // Urutkan berdasarkan jumlah terjual
        List<Map.Entry<String, int[]>> sorted = rekap.entrySet().stream()
            .sorted((a, b) -> b.getValue()[0] - a.getValue()[0])
            .limit(topN)
            .collect(Collectors.toList());
 
        System.out.println("\n" + "=".repeat(56));
        System.out.printf("       TOP %d BARANG PALING LAKU%n", topN);
        System.out.println("=".repeat(56));
        System.out.printf("%-4s %-8s %-22s %8s  %12s%n",
            "No.", "Kode", "Nama Barang", "Terjual", "Pendapatan");
        System.out.println("-".repeat(56));
 
        int no = 1;
        for (Map.Entry<String, int[]> entry : sorted) {
            String kode  = entry.getKey();
            int    jual  = entry.getValue()[0];
            double pend  = pendapatan.getOrDefault(kode, 0.0);
            String nama  = barangRepo.findByKode(kode) != null
                ? barangRepo.findByKode(kode).getNamaBarang() : kode;
 
            System.out.printf("%-4d %-8s %-22s %8d  %12.0f%n",
                no++, kode, nama, jual, pend);
        }
        System.out.println("=".repeat(56));
    }
 
    // -------------------------------------------------------
    // 4. Stok barang menipis (stok <= batas)
    // -------------------------------------------------------
    public void tampilkanStokMenupis(int batas) {
        var list = barangRepo.findAll().stream()
            .filter(b -> b.getStok() <= batas)
            .sorted(Comparator.comparingInt(b -> b.getStok()))
            .collect(Collectors.toList());
 
        System.out.println("\n" + "=".repeat(50));
        System.out.println("  PERINGATAN: STOK MENIPIS (stok <= " + batas + ")");
        System.out.println("=".repeat(50));
 
        if (list.isEmpty()) {
            System.out.println("Semua stok masih aman.");
        } else {
            System.out.printf("%-8s %-24s %6s%n", "Kode", "Nama Barang", "Stok");
            System.out.println("-".repeat(50));
            for (var b : list) {
                System.out.printf("%-8s %-24s %6d%n",
                    b.getKodeBarang(), b.getNamaBarang(), b.getStok());
            }
        }
        System.out.println("=".repeat(50));
    }
}