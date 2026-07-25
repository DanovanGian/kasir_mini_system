package minimarket.model;

public class Barang {
    private int id;
    private String kodeBarang;
    private String namaBarang;
    private double harga;
    private int stok;
    private String kategori;

    public Barang(int id, String kodeBarang, String namaBarang, double harga, int stok, String kategori) {
        this.id = id;
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.harga = harga;
        this.stok = stok;
        this.kategori = kategori;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public double getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    public String getKategori() {
        return kategori;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public void setNamaBarang(String nama) {
        this.namaBarang = nama;
    }

    public boolean isStokTersedia(int jumlah) {
        return stok >= jumlah;
    }

    public void kurangiStok(int jumlah) {
        if (!isStokTersedia(jumlah))
            throw new RuntimeException("Stok tidak mencukupi!");
        this.stok -= jumlah;
    }

    public String getInfo() {
        return String.format("[%s] %-25s Rp%,.0f  Stok: %d", kodeBarang, namaBarang, harga, stok);
    }
}