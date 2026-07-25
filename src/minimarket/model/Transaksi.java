package minimarket.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Transaksi {
    private int idTransaksi;
    private User kasir;
    private LocalDateTime tanggal;
    private List<DetailTransaksi> detailList;
    private double total;
    private String status; // "selesai" / "batal"

    public Transaksi(int idTransaksi, User kasir) {
        this.idTransaksi = idTransaksi;
        this.kasir = kasir;
        this.tanggal = LocalDateTime.now();
        this.detailList = new ArrayList<>();
        this.total = 0;
        this.status = "proses";
    }

    public void tambahDetail(DetailTransaksi detail) {
        detailList.add(detail);
        total += detail.getSubtotal();
    }

    public void hapusDetail(int index) {
        if (index >= 0 && index < detailList.size()) {
            total -= detailList.get(index).getSubtotal();
            detailList.remove(index);
        }
    }

    public void selesaikan() {
        this.status = "selesai";
    }

    public void batal() {
        this.status = "batal";
    }

    // Getter
    public int getIdTransaksi() {
        return idTransaksi;
    }

    public User getKasir() {
        return kasir;
    }

    public LocalDateTime getTanggal() {
        return tanggal;
    }

    public List<DetailTransaksi> getDetailList() {
        return detailList;
    }

    public double getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }

    public String getTanggalFormatted() {
        return tanggal.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}
