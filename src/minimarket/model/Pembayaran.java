package minimarket.model;

public class Pembayaran {
    private double totalBayar;
    private double uangBayar;
    private double kembalian;

    public Pembayaran(double totalBayar, double uangBayar) {
        this.totalBayar = totalBayar;
        this.uangBayar = uangBayar;
        this.kembalian = uangBayar - totalBayar;
    }

    public boolean isValid() {
        return uangBayar >= totalBayar;
    }

    public double getTotalBayar() {
        return totalBayar;
    }

    public double getUangBayar() {
        return uangBayar;
    }

    public double getKembalian() {
        return kembalian;
    }

}