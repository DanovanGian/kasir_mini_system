package minimarket.repository;

import minimarket.model.Barang;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BarangRepository {
    private List<Barang> barangList = new ArrayList<>();
    private int nextId = 1;

    public BarangRepository() {
        // Data dummy
        tambah(new Barang(nextId++, "MKN001", "Indomie Goreng", 3500, 100, "Makanan"));
        tambah(new Barang(nextId++, "MNM001", "Teh Botol 350ml", 5000, 120, "Minuman"));
        tambah(new Barang(nextId++, "MNM002", "Aqua 600ml", 4000, 200, "Minuman"));
        tambah(new Barang(nextId++, "SNK001", "Chitato 75g", 10000, 50, "Snack"));
        tambah(new Barang(nextId++, "KBR001", "Sabun Lifebuoy", 8500, 70, "Kebersihan"));
    }

    public void tambah(Barang barang) {
        barangList.add(barang);
    }

    public List<Barang> findAll() {
        return barangList;
    }

    public Barang findByKode(String kode) {
        return barangList.stream()
                .filter(b -> b.getKodeBarang().equalsIgnoreCase(kode))
                .findFirst().orElse(null);
    }

    public Barang findById(int id) {
        return barangList.stream()
                .filter(b -> b.getId() == id)
                .findFirst().orElse(null);
    }

    public List<Barang> findByNama(String keyword) {
        return barangList.stream()
                .filter(b -> b.getNamaBarang().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public boolean hapus(String kode) {
        return barangList.removeIf(b -> b.getKodeBarang().equalsIgnoreCase(kode));
    }

}