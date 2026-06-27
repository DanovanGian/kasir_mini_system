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
    //getter dan setter 
    public int getidTransaksi () { return idTransaksi; }
    public int getidBarang () { return idBarang; }
    public int getjumlah () { return jumlah; }
    public double gethargaSatuan () { return hargaSatuan; }

    public void setidTransaksi ( int idTransaksi ){
        this.idTransaksi = idTransaksi;
    }
    public void setidBarang ( int Barang ){
        this.idBarang = idBarang;
    }
    public void setjumlah ( int Jumlah ){
        this.jumlah  = jumlah;
    }
    public void sethargaSatuan ( int hargaSatuan ){
        this.hargaSatuan = hargaSatuan;
    }
    
    
}
