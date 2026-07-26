package minimarket.util;
 
import minimarket.model.DetailTransaksi;
import minimarket.model.Pembayaran;
import minimarket.model.Transaksi;
public class StrukPrinter {
 
    public static void cetak(Transaksi trx, Pembayaran bayar) {
        int LEBAR = 40;
        System.out.println(FormatHelper.garis(LEBAR));
        System.out.printf("%s%n", center("MINIMARKET DEL", LEBAR));
        System.out.printf("%s%n", center("Jl. Sudirman No. 1", LEBAR));
        System.out.println(FormatHelper.garis(LEBAR));
        System.out.printf("Kasir   : %s%n", trx.getKasir().getNama());
        System.out.printf("Tanggal : %s%n", trx.getTanggalFormatted());
        System.out.printf("No. Trx : TRX-%04d%n", trx.getIdTransaksi());
        System.out.println(FormatHelper.garisTengah(LEBAR));