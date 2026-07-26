package minimarket.util;
 
import java.text.NumberFormat;
import java.util.Locale;
 
public class FormatHelper {
    private static final NumberFormat IDR = NumberFormat.getInstance(new Locale("id", "ID"));
 
    public static String rupiah(double amount) {
        return "Rp" + IDR.format((long) amount);
    }
 
    public static String garis(int panjang) {
        return "=".repeat(panjang);
    }
 
    public static String garisTengah(int panjang) {
        return "-".repeat(panjang);
    }
}