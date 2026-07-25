package minimarket.model;
 
public class User {
    private int id;
    private String username;
    private String password;
    private String nama;
    private String role; // "admin" atau "kasir"
 
    public User(int id, String username, String password, String nama, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.role = role;
    }
 
    // Getter & Setter
    public int getId()           { return id; }
    public String getUsername()  { return username; }
    public String getPassword()  { return password; }
    public String getNama()      { return nama; }
    public String getRole()      { return role; }
 
    public boolean isAdmin()     { return "admin".equals(role); }
    public boolean isKasir()     { return "kasir".equals(role); }
 
    @Override
    public String toString() {
        return "[" + role.toUpperCase() + "] " + nama + " (" + username + ")";
    }
}