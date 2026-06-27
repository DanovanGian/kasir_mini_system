package minimarket.model;

public class DetailTransaksi {
    private int idTransaksi;
    private int idBarang;
    private int jumlah;
    private double hargaSatuan;

    public DetailTransaksi ( int idTransaksi, int idBarang, int Jumlah, double hargaSatuan){
        this.idTransaksi = idTransaksi;
        this.idBarang = idBarang;
        this.jumlah = jumlah;
        this.hargaSatuan = hargaSatuan;


        
    }
    
    
}
