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
 
        for (DetailTransaksi d : trx.getDetailList()) {
            String nama = d.getBarang().getNamaBarang();
            if (nama.length() > LEBAR) nama = nama.substring(0, LEBAR - 3) + "...";
            System.out.println(nama);
            System.out.printf("  %3d x %-10s %12s%n",
                d.getJumlah(),
                FormatHelper.rupiah(d.getHargaSatuan()),
                FormatHelper.rupiah(d.getSubtotal()));
        }
 
        System.out.println(FormatHelper.garisTengah(LEBAR));
        System.out.printf("%-20s %18s%n", "Total", ":", FormatHelper.rupiah(bayar.getTotalBayar()));
        System.out.printf("%-20s %18s%n", "Bayar", ":", FormatHelper.rupiah(bayar.getUangBayar()));
        System.out.printf("%-20s %18s%n", "Kembalian", ":", FormatHelper.rupiah(bayar.getKembalian()));
        System.out.println(FormatHelper.garis(LEBAR));
        System.out.printf("%s%n", center("Terima Kasih!", LEBAR));
        System.out.printf("%s%n", center("Selamat Berbelanja Kembali :)", LEBAR));
        System.out.println(FormatHelper.garis(LEBAR));
    }
 
    private static String center(String text, int width) {
        int pad = (width - text.length()) / 2;
        return " ".repeat(Math.max(0, pad)) + text;
    }
}