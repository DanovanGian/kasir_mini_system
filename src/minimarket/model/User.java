//sebagai class parent untuk admin dan kasir dan jika ada role baru akan di ambil dari sini berisi class pengguna/user

package minimarket.model;

public class User {
    private int id;
    private String username;
    private String password;
    private String role;
    private String nama;

    public User(int id, String username, String password, String role, String nama){
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role; //admin atau kasir atau role lain jika in case ada role baru
        this.nama = nama;

    }

    //getter dan setter untuk semua atribut yang ada di class user
    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() {return password;}
    public String getRole() { return role; }
    public String getNama() { return nama; }

    public boolean isAdmin() { return "admin".equals(role); }
    public boolean isKasir() { return "kasir".equals(role); }

    

}