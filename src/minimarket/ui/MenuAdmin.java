package minimarket.ui;

import minimarket.model.Barang;
import minimarket.model.User;
import minimarket.repository.BarangRepository;
import minimarket.util.InputHelper;
import java.util.List;

public class MenuAdmin {
    private User admin;
    private BarangRepository barangRepo = new BarangRepository();
    private InputHelper input = new InputHelper();

    public MenuAdmin(User admin) {
        this.admin = admin;
    }

    public void tampilkan() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== MENU ADMIN ===");
            System.out.println("1. Lihat Daftar Barang");
            System.out.println("2. Tambah Barang");
            System.out.println("3. Edit Barang");
            System.out.println("4. Hapus Barang");
            System.out.println("5. Cari Barang");
            System.out.println("0. Logout");

            int pilihan = input.getInt("Pilihan: ");
            switch (pilihan) {
                case 1 -> lihatBarang();
                case 2 -> tambahBarang();
                case 3 -> editBarang();
                case 4 -> hapusBarang();
                case 5 -> cariBarang();
                case 0 -> running = false;
                default -> System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private void lihatBarang() {
        System.out.println("\n--- DAFTAR BARANG ---");
        List<Barang> list = barangRepo.findAll();
        if (list.isEmpty()) {
            System.out.println("Belum ada barang.");
            return;
        }
        System.out.printf("%-8s %-25s %12s %8s%n", "Kode", "Nama", "Harga", "Stok");
        System.out.println("-".repeat(58));
        list.forEach(b -> System.out.printf("%-8s %-25s %12.0f %8d%n",
                b.getKodeBarang(), b.getNamaBarang(), b.getHarga(), b.getStok()));
    }

    private void tambahBarang() {
        System.out.println("\n--- TAMBAH BARANG ---");
        String kode = input.getString("Kode Barang : ");
        String nama = input.getString("Nama Barang : ");
        double harga = input.getDouble("Harga       : ");
        int stok = input.getInt("Stok        : ");
        String kategori = input.getString("Kategori    : ");

        barangRepo.tambah(new Barang(barangRepo.nextId(), kode, nama, harga, stok, kategori));
        System.out.println("Barang berhasil ditambahkan!");
    }

    private void editBarang() {
        System.out.println("\n--- EDIT BARANG ---");
        String kode = input.getString("Kode Barang : ");
        Barang barang = barangRepo.findByKode(kode);
        if (barang == null) {
            System.out.println("Barang tidak ditemukan!");
            return;
        }

        System.out.println("Barang: " + barang.getInfo());
        double hargaBaru = input.getDouble("Harga baru  : ");
        int stokBaru = input.getInt("Stok baru   : ");
        barang.setHarga(hargaBaru);
        barang.setStok(stokBaru);
        System.out.println("Barang berhasil diupdate!");
    }

    private void hapusBarang() {
        String kode = input.getString("Kode Barang yang dihapus: ");
        if (barangRepo.hapus(kode))
            System.out.println("Barang dihapus!");
        else
            System.out.println("Barang tidak ditemukan!");
    }

    private void cariBarang() {
        String keyword = input.getString("Cari barang (nama/kode): ");
        List<Barang> hasil = barangRepo.findByNama(keyword);
        if (hasil.isEmpty())
            System.out.println("Barang tidak ditemukan.");
        else
            hasil.forEach(b -> System.out.println(b.getInfo()));
    }
}