package minimarket.service;
 
import minimarket.model.User;
import minimarket.repository.UserRepository;
 
public class AuthService {
 
    private static final int MAKS_PERCOBAAN = 3;
 
    private UserRepository userRepo;
    private User userAktif;   // siapa yang sedang login
 
    public AuthService(UserRepository userRepo) {
        this.userRepo = userRepo;
        this.userAktif = null;
    }
 
    // -------------------------------------------------------
    // Login — kembalikan User jika berhasil, null jika gagal
    // -------------------------------------------------------
    public User login(String username, String password) {
        if (username == null || username.isBlank()) return null;
        if (password == null || password.isBlank()) return null;
 
        User user = userRepo.login(username, password);
        if (user != null) {
            this.userAktif = user;
            System.out.println("Login berhasil. Selamat datang, " + user.getNama() + "!");
        }
        return user;
    }
 
    // -------------------------------------------------------
    // Login dengan batas percobaan — dipakai di MenuUtama
    // -------------------------------------------------------
    public User loginDenganPercobaan(java.util.Scanner scanner) {
        int percobaan = 0;
        while (percobaan < MAKS_PERCOBAAN) {
            System.out.println("\n=== LOGIN ===");
            System.out.print("Username : ");
            String username = scanner.nextLine().trim();
            System.out.print("Password : ");
            String password = scanner.nextLine().trim();
 
            User user = login(username, password);
            if (user != null) return user;
 
            percobaan++;
            int sisa = MAKS_PERCOBAAN - percobaan;
            System.out.println("Username atau password salah!"
                + (sisa > 0 ? " Sisa percobaan: " + sisa : ""));
        }
        System.out.println("Terlalu banyak percobaan. Program ditutup.");
        return null;
    }
 
    // -------------------------------------------------------
    // Logout
    // -------------------------------------------------------
    public void logout() {
        if (userAktif != null) {
            System.out.println("Sampai jumpa, " + userAktif.getNama() + "!");
            userAktif = null;
        }
    }
 
    // -------------------------------------------------------
    // Cek akses
    // -------------------------------------------------------
    public boolean sudahLogin() {
        return userAktif != null;
    }
 
    public boolean isAdmin() {
        return sudahLogin() && userAktif.isAdmin();
    }
 
    public boolean isKasir() {
        return sudahLogin() && userAktif.isKasir();
    }
 
    public User getUserAktif() {
        return userAktif;
    }
}