package minimarket.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Transaksi{
    private int idTransaksi;
    private User kasir;
    private LocalDateTime tanggal;
    private List<DetailTransaksi> detailList;
    private double total;
    private String status; // "selesai" / "batal"




    
}