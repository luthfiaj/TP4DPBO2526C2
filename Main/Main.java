public class Main {
    private String id;
    private String nama;
    private int tahunLahir;
    private String kategori;
    private String email;

    public Main(String id, String nama, int tahunLahir, String kategori, String email) {
        this.id = id;
        this.nama = nama;
        this.tahunLahir = tahunLahir;
        this.kategori = kategori;
        this.email = email;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
    public int getTahunLahir() { return tahunLahir; }
    public String getKategori() { return kategori; }
    public String getEmail() { return email; }

    public void setNama(String nama) { this.nama = nama; }
    public void setTahunLahir(int tahunLahir) { this.tahunLahir = tahunLahir; }
    public void setKategori(String kategori) { this.kategori = kategori; }
    public void setEmail(String email) { this.email = email; }

    public int getUmur() {
        return java.time.Year.now().getValue() - tahunLahir;
    }

    // Dipakai untuk mengisi baris tabel
    public Object[] toRow() {
        return new Object[]{id, nama, tahunLahir, kategori, email};
    }
}