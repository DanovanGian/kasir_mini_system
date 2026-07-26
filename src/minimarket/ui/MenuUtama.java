package minimarket.ui;

import minimarket.model.User;
import minimarket.repository.UserRepository;
import minimarket.util.InputHelper;

public class MenuUtama {
    private UserRepository userRepo = new UserRepository();
    private InputHelper input = new InputHelper();

    public void mulai() {
        System.out.println("╔════════════════════════════════╗");
        System.out.println("║     SISTEM KASIR MINIMARKET    ║");
        System.out.println("║           DEL Store            ║");
        System.out.println("╚════════════════════════════════╝");

        User user = login();
        if (user == null) {
            System.out.println("Login gagal. Program ditutup.");
            return;
        }

        System.out.printf("%nSelamat datang, %s!%n%n", user.getNama());

        if (user.isAdmin()) {
            new MenuAdmin(user).tampilkan();
        } else {
            new MenuKasir(user).tampilkan();
        }
    }

    private User login() {
        int percobaan = 0;
        while (percobaan < 3) {
            System.out.println("\n=== LOGIN ===");
            String username = input.getString("Username : ");
            String password = input.getString("Password : ");
            User user = userRepo.login(username, password);
            if (user != null)
                return user;
            percobaan++;
            System.out.printf("Login gagal! Sisa percobaan: %d%n", 3 - percobaan);
        }
        return null;
    }
}