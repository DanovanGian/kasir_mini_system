package minimarket.service;
 
import minimarket.model.Barang;
import minimarket.repository.BarangRepository;
 
import java.util.List;
 
public class BarangService {
 
    private BarangRepository barangRepo;
 
    public BarangService(BarangRepository barangRepo) {
        this.barangRepo = barangRepo;
    }
 
    // -------------------------------------------------------
    // Tambah barang baru — dengan validasi lengkap
    // -------------------------------------------------------
    public boolean tambahBarang(String kode, String nama, double harga,
                                int stok, String kategori) {
        // Validasi kode unik
        if (barangRepo.findByKode(kode) != null) {
            System.out.println("Gagal: Kode barang '" + kode + "' sudah dipakai.");
            return false;
        }
        // Validasi nilai
        if (nama == null || nama.isBlank()) {
            System.out.println("Gagal: Nama barang tidak boleh kosong.");
            return false;
        }
        if (harga < 0) {
            System.out.println("Gagal: Harga tidak boleh negatif.");
            return false;
        }
        if (stok < 0) {
            System.out.println("Gagal: Stok tidak boleh negatif.");
            return false;
        }
 
        Barang baru = new Barang(
            barangRepo.nextId(), kode.toUpperCase(),
            nama, harga, stok, kategori
        );
        barangRepo.tambah(baru);
        System.out.println("Barang '" + nama + "' berhasil ditambahkan.");
        return true;
    }
 
    // -------------------------------------------------------
    // Edit harga dan stok barang
    // -------------------------------------------------------
    public boolean editBarang(String kode, double hargaBaru, int stokBaru,
                              String namaBaru) {
        Barang barang = barangRepo.findByKode(kode);
        if (barang == null) {
            System.out.println("Barang dengan kode '" + kode + "' tidak ditemukan.");
            return false;
        }
        if (hargaBaru < 0) {
            System.out.println("Gagal: Harga tidak boleh negatif.");
            return false;
        }
        if (stokBaru < 0) {
            System.out.println("Gagal: Stok tidak boleh negatif.");
            return false;
        }
 
        barang.setHarga(hargaBaru);
        barang.setStok(stokBaru);
        if (namaBaru != null && !namaBaru.isBlank()) {
            barang.setNamaBarang(namaBaru);
        }
        System.out.println("Barang '" + barang.getNamaBarang() + "' berhasil diupdate.");
        return true;
    }
 
    // -------------------------------------------------------
    // Hapus barang berdasarkan kode
    // -------------------------------------------------------
    public boolean hapusBarang(String kode) {
        Barang barang = barangRepo.findByKode(kode);
        if (barang == null) {
            System.out.println("Barang dengan kode '" + kode + "' tidak ditemukan.");
            return false;
        }
        barangRepo.hapus(kode);
        System.out.println("Barang '" + barang.getNamaBarang() + "' berhasil dihapus.");
        return true;
    }
 
    // -------------------------------------------------------
    // Update stok saja (tanpa ubah harga/nama)
    // -------------------------------------------------------
    public boolean updateStok(String kode, int stokBaru) {
        Barang barang = barangRepo.findByKode(kode);
        if (barang == null) {
            System.out.println("Barang tidak ditemukan.");
            return false;
        }
        if (stokBaru < 0) {
            System.out.println("Stok tidak boleh negatif.");
            return false;
        }
        barang.setStok(stokBaru);
        System.out.printf("Stok '%s' diupdate menjadi %d.%n",
            barang.getNamaBarang(), stokBaru);
        return true;
    }
 
    // -------------------------------------------------------
    // Cari & lihat
    // -------------------------------------------------------
    public List<Barang> semuaBarang() {
        return barangRepo.findAll();
    }
 
    public Barang cariByKode(String kode) {
        return barangRepo.findByKode(kode);
    }
 
    public List<Barang> cariByNama(String keyword) {
        return barangRepo.findByNama(keyword);
    }
 
    // -------------------------------------------------------
    // Tampilkan tabel barang ke console
    // -------------------------------------------------------
    public void tampilkanDaftarBarang() {
        List<Barang> list = barangRepo.findAll();
        if (list.isEmpty()) {
            System.out.println("Belum ada barang.");
            return;
        }
        System.out.println("\n" + "=".repeat(62));
        System.out.printf("%-8s %-24s %12s %6s  %-12s%n",
            "Kode", "Nama Barang", "Harga", "Stok", "Kategori");
        System.out.println("-".repeat(62));
        for (Barang b : list) {
            // tandai stok menipis
            String stokStr = b.getStok() <= 5
                ? b.getStok() + " (!!)"
                : String.valueOf(b.getStok());
            System.out.printf("%-8s %-24s %12.0f %6s  %-12s%n",
                b.getKodeBarang(), b.getNamaBarang(),
                b.getHarga(), stokStr, b.getKategori());
        }
        System.out.println("=".repeat(62));
        System.out.printf("Total %d barang.%n", list.size());
    }
}