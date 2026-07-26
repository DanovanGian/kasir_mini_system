package minimarket.model;

public class DetailTransaksi {
    private Barang barang;
    private int jumlah;
    private double hargaSatuan;
    private double subTotal;


    public DetailTransaksi ( Barang Barang, int Jumlah, double hargaSatuan, double subTotal){
        this.barang = barang;
        this.jumlah = jumlah;
        this.hargaSatuan = hargaSatuan;
        this.subTotal = subTotal;

    }
    // overload untuk compatibility dengan pemanggilan existing new DetailTransaksi(barang, jumlah)
    public DetailTransaksi(Barang barang, int jumlah) {
        this(barang, jumlah, barang.getHarga(), barang.getHarga() * jumlah);
    }

    // getter yang dibutuhkan oleh banyak file
    public int getJumlah() {
        return jumlah;
    }

    //getter dan setter 
    public Barang getBarang () { return barang; }
    public int getjumlah () { return jumlah; }
    public double getHargaSatuan () { return hargaSatuan; }
    public double getSubtotal () { return subTotal; }


    

}
