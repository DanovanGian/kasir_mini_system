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
    //getter dan setter 
    public Barang getBarang () { return barang; }
    public int getjumlah () { return jumlah; }
    public double getHargaSatuan () { return hargaSatuan; }
    public double getSubtotal () { return subTotal; }


    

}
