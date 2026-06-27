package minimarket.model;

public class Barang {
    private int id;
    private String kodeBarang;
    private String  namaBarang;
    private double hargaBarang;
    private int stok;
    private String kategori;

    public Barang(int id, String kodeBarang, String namaBarang, int hargaBarang, int stok, String kategori)
    {
        this.id = id;
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
        this.stok = stok;
        this.kategori = kategori;
    

    }
    
}
