package minimarket.repository;
 
import minimarket.model.Transaksi;
 
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
 
public class TransaksiRepository {
 
    private List<Transaksi> transaksiList = new ArrayList<>();
 
    // -------------------------------------------------------
    // Simpan transaksi baru
    // -------------------------------------------------------
    public void simpan(Transaksi trx) {
        transaksiList.add(trx);
    }
 
    // -------------------------------------------------------
    // Ambil semua transaksi
    // -------------------------------------------------------
    public List<Transaksi> findAll() {
        return new ArrayList<>(transaksiList); // defensive copy
    }
 
    // -------------------------------------------------------
    // Cari berdasarkan ID
    // -------------------------------------------------------
    public Transaksi findById(int id) {
        return transaksiList.stream()
            .filter(t -> t.getIdTransaksi() == id)
            .findFirst()
            .orElse(null);
    }
 
    // -------------------------------------------------------
    // Filter berdasarkan status ("selesai" / "batal")
    // -------------------------------------------------------
    public List<Transaksi> findByStatus(String status) {
        return transaksiList.stream()
            .filter(t -> t.getStatus().equalsIgnoreCase(status))
            .collect(Collectors.toList());
    }
 
    // -------------------------------------------------------
    // Filter berdasarkan nama kasir
    // -------------------------------------------------------
    public List<Transaksi> findByKasir(String usernameKasir) {
        return transaksiList.stream()
            .filter(t -> t.getKasir().getUsername()
                          .equalsIgnoreCase(usernameKasir))
            .collect(Collectors.toList());
    }
 
    // -------------------------------------------------------
    // Hitung total pendapatan (hanya transaksi selesai)
    // -------------------------------------------------------
    public double hitungTotalPendapatan() {
        return transaksiList.stream()
            .filter(t -> "selesai".equals(t.getStatus()))
            .mapToDouble(Transaksi::getTotal)
            .sum();
    }
 
    // -------------------------------------------------------
    // Jumlah transaksi (semua / per status)
    // -------------------------------------------------------
    public int jumlahTransaksi() {
        return transaksiList.size();
    }
 
    public int jumlahTransaksiByStatus(String status) {
        return (int) transaksiList.stream()
            .filter(t -> t.getStatus().equalsIgnoreCase(status))
            .count();
    }
}